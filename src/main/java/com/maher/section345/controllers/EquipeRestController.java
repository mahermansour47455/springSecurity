package com.maher.section345.controllers;


import com.maher.section345.entities.Equipe;
import com.maher.section345.entities.League;
import com.maher.section345.service.EquipeService;
import com.maher.section345.service.LeagueService;
import org.apache.coyote.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class EquipeRestController {

    @Autowired
    EquipeService equipeService;
    @Autowired
    LeagueService leagueService;

    @RequestMapping(path = "all",method= RequestMethod.GET)
    public List<Equipe> getAllEquipes(){
        return equipeService.getAllEquipes();
    }
    @RequestMapping(value="/getbyid/{id}" ,method = RequestMethod.GET )
    public Equipe getEquipe(@PathVariable("id") long id){
        return equipeService.getEquipe(id);
    }
    @RequestMapping(path = "/addequipe",method = RequestMethod.POST)
    public Equipe saveEquipe(@RequestBody Equipe e){
        return equipeService.saveEquipe(e);
    }
    @RequestMapping(path = "/updateequipe",method = RequestMethod.PUT)
    public Equipe updateEquipe(@RequestBody Equipe e)
    {
        return equipeService.updateEquipe(e);

    }
    @RequestMapping(value="/deleteequipe/{id}",method = RequestMethod.DELETE)
    public void  deleteEquipe(@PathVariable("id") long id)
    {
         equipeService.deleteEquipeById(id);

    }
    @RequestMapping(value="/eqlg/{idLeg}",method = RequestMethod.GET)
    public List<Equipe> getProduitsByCatId(@PathVariable("idLeg") Long idLeg) {
        return equipeService.findByLeagueIdLeg(idLeg);
    }
    @RequestMapping(value="/leg",method = RequestMethod.GET)
    public List<League>getAllleagues(){
        return leagueService.getAllLeagues();
    }
    @RequestMapping(value="/addleg",method = RequestMethod.POST)
    public League saveLeague(@RequestBody League l){
        return leagueService.saveLeague(l);
    }
    @RequestMapping(value="equipe/{nom}" ,method = RequestMethod.GET )
    public List<Equipe> getEquipe(@PathVariable("nom") String nom){
        return equipeService.findByNomEquipeContains(nom);
    }


}
