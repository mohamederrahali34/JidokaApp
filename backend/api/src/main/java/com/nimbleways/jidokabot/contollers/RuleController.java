package com.nimbleways.jidokabot.contollers;

import com.nimbleways.jidokabot.dto.RuleDto;
import com.nimbleways.jidokabot.entities.Rule;
import com.nimbleways.jidokabot.services.IRuleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/rules")
public class RuleController {
    private final IRuleService ruleService;

    public RuleController(final IRuleService ruleService) {
        this.ruleService = ruleService;
    }

    @PostMapping("/new")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Rule> addRuleToBoard(@RequestBody @Valid final RuleDto ruleDto) {
        final Rule rule = ruleService.addRuleToBoard(ruleDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(rule);
    }
    @GetMapping("/rule")
    public Rule getRuleById(@RequestParam final String id,@RequestParam final  String type){

       return  ruleService.getRuleByTypeAndId(type,id);
    }
    @PutMapping("/update")
    public ResponseEntity<Rule> updateRule(@RequestBody @Valid final RuleDto ruleDto) {
        return ResponseEntity.status(HttpStatus.OK).body(ruleService.updateRule(ruleDto));
    }
    @PutMapping("/activate")
    public ResponseEntity<Rule> toggleActivateRule(@RequestParam @Valid final String id) {
        return ResponseEntity.status(HttpStatus.OK).body(ruleService.toggleActivateRule(id));
    }
    @DeleteMapping("/delete")
    @ResponseStatus(HttpStatus.OK)
    public void deleteRule(@RequestBody @Valid final RuleDto ruleDto) {
        ruleService.deleteRule(ruleDto);
    }

}