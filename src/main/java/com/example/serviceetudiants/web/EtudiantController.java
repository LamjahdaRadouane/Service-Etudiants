package com.example.serviceetudiants.web;

import com.example.serviceetudiants.entities.Etudiant;
import com.example.serviceetudiants.repositories.EtudiantRepository;
import lombok.AllArgsConstructor;
import org.keycloak.KeycloakPrincipal;
import org.keycloak.KeycloakSecurityContext;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.hateoas.PagedModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@AllArgsConstructor
public class EtudiantController {
    private EtudiantRepository etudiantRepository;
    @GetMapping(path = "/index")
    public String etudiants(Model model,
                            @RequestParam(name = "page",defaultValue = "0") int page,
                            @RequestParam(name = "size",defaultValue = "5") int size,
                            @RequestParam(name = "keyword",defaultValue = "") String keyword
    ){
        Page<Etudiant> pageEtudiants=etudiantRepository.findByNomContains(keyword, PageRequest.of(page,size));
        model.addAttribute("listEtudiants",pageEtudiants.getContent());
        model.addAttribute("pages",new int[pageEtudiants.getTotalPages()]);
        model.addAttribute("currentPage",page);
        model.addAttribute("keyword",keyword);
        return "etudiants";
    }

    @GetMapping("/delete")
    public String delete(Long id, String keyword, int page) {
        etudiantRepository.deleteById(id);
        return "redirect:/index?page="+page+"&keyword="+keyword;
    }
    @GetMapping("/")
    public String home() {
        return "template";
    }
    @GetMapping("/etudiants")
    @ResponseBody
    public List<Etudiant> lisEtudiants(){
        return etudiantRepository.findAll();
    }

    @RequestMapping(value="/form",method= RequestMethod.GET)
    public String formEtudiant(Model model){
        model.addAttribute("etudiant", new Etudiant());
        return "FormEtudiant";
    }
    @RequestMapping(value="/SaveEtudiant",method= RequestMethod.POST)
    public String save(@Valid Etudiant et, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "FormEtudiant";
        }
        etudiantRepository.save(et);
        return "redirect:/index";
    }
    @RequestMapping(value="/edit")
    public String edit(Long id, Model model){
        Etudiant et = etudiantRepository.getOne(id);
        model.addAttribute("etudiant", et);
        return "EditEtudiant";
    }
    @RequestMapping(value="/UpdateEtudiant",method= RequestMethod.POST)
    public String update(@Valid Etudiant et, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "EditEtudiant";
        }
        etudiantRepository.save(et);
        return "redirect:/index";
    }

    @ExceptionHandler(Exception.class)
    public String exceptionHandler(Exception e, Model model){
        model.addAttribute("errorMessage", "Not Authorized");
        return "errors";
    }
    @GetMapping("/export")
    public void exportToCsv(HttpServletResponse response) throws IOException {
        response.setContentType("text/csv");
        String fileName = "etudiants.csv";
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=" +fileName;
        response.setHeader(headerKey, headerValue);
        List<Etudiant> listEtudiants = etudiantRepository.findAll();
        ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(), CsvPreference.STANDARD_PREFERENCE);
        String[] csvHeader = {"ID", "Nom", "Prenom", "Massar", "Email", "Filiere", "Session", "Semestre"};
        String[] nameMapping = {"id", "nom", "prenom", "massar", "email", "filiere", "session", "semestre"};
        csvWriter.writeHeader(csvHeader);
        for (Etudiant etud : listEtudiants) {
            csvWriter.write(etud, nameMapping);
        }
        csvWriter.close();
    }
/*

    Classe KeycloakRestTemplate l'authentification est gérée automatiquement lorsque le service
    effectuant l'appel d'API et le service appelé sont protégés par l'authentification Keycloak.
    @GetMapping("/projetRecherche")
    public String projetRecherche(Model model){
        PagedModel<Entity> pageprojetRecherche = keycloakRestTemplate.getForObject("http://localhost:8024/index", PagedModel.class);
        model.addAttribute("projetRecherche", pageMs);
        return "ms";
    }

    @GetMapping("/jwt")
    @ResponseBody
    public Map<String, String> map(HttpServletRequest request){
        KeycloakAuthenticationToken token = (KeycloakAuthenticationToken) request.getUserPrincipal();
        KeycloakPrincipal principal = (KeycloakPrincipal) token.getPrincipal();
        KeycloakSecurityContext keycloakSecurityContext = principal.getKeycloakSecurityContext();
        Map<String, String> map = new HashMap<>();
        map.put("access_token", keycloakSecurityContext.getTokenString());
        return map;
    }*/
}