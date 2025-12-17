package com.example.product.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.product.entity.PricingRule;
import com.example.product.entity.RuleType;
import com.example.product.repository.PricingRuleRepository;

import jakarta.validation.Valid;



@Controller
@RequestMapping("/ui/rules")
public class PricingRuleUiController {

    private final PricingRuleRepository repository;

    public PricingRuleUiController(PricingRuleRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("rules", repository.findAll());
        return "rule/list";
    }

    @GetMapping("/add")
    public String add(Model model) {
        model.addAttribute("rule", new PricingRule());
        model.addAttribute("types", RuleType.values());
        return "rule/form";
    }

    @PostMapping("/add")
    public String save(@Valid @ModelAttribute PricingRule rule,
                       BindingResult result,
                       Model model) {

        if (result.hasErrors()) {
            model.addAttribute("types", RuleType.values());
            return "rule/form";
        }

        repository.save(rule);
        return "redirect:/ui/rules";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
        PricingRule rule = repository.findById(id)
                .orElseThrow();
        model.addAttribute("rule", rule);
        model.addAttribute("types", RuleType.values());
        return "rule/form";
    }

    @GetMapping("/delete/{id}")
    public String confirm(@PathVariable Long id, Model model) {
        model.addAttribute("rule", repository.findById(id).orElseThrow());
        return "rule/delete";
    }

    @PostMapping("/delete")
    public String delete(@RequestParam Long id) {
        repository.deleteById(id);
        return "redirect:/ui/rules";
    }
}

