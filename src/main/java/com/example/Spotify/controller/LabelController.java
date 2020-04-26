package com.example.Spotify.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.Spotify.entities.Label;

import com.example.Spotify.repositories.LabelRepository;


@Controller
@RequestMapping(path="/spotif")
public class LabelController {
    @Autowired
    private LabelRepository labelRepository;

    private ArtistController artistController;
    Label temp = new Label();

    @PostMapping(path="/label/add")
    public @ResponseBody ResponseEntity<Label> addLabel(@RequestParam String labelName){
        try{
            Label labelData=new Label();
            labelData.setName(labelName);
            labelRepository.save(labelData);
            return ResponseEntity.ok(labelData);
        }catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping(path="/label/get")
    public @ResponseBody ResponseEntity<Iterable<Label>> getAllLabel(){
        try{
            return ResponseEntity.ok(labelRepository.findAll());
        }
        catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping(path="/label/get/{labelId}")
    public @ResponseBody ResponseEntity<Label> getLabel(@PathVariable int labelId){
        try{
            Label labelData = labelRepository.findById(labelId).get();
            return ResponseEntity.ok(labelData);
        }
        catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping(path="/label/update/{id}")
    public @ResponseBody ResponseEntity<Label> updateLabel(@PathVariable int id, @RequestParam String labelName) {
        try {
        Label labelData = new Label();
        labelData.setId(id);
        labelData.setName(labelName);
        labelRepository.save(labelData);
        return ResponseEntity.ok(labelData);   
          }catch (Exception e) {
            return ResponseEntity.notFound().build();
          }
    }


    @DeleteMapping(path="/label/delete/{id}")
    public @ResponseBody ResponseEntity<Void> deleteLabel(@PathVariable int labelId){
        try{
            artistController.deleteArtistByLabel(labelId);
            labelRepository.deleteById(labelId);

            return ResponseEntity.ok().build();
        }
        catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }
    

}