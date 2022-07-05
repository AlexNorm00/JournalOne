package com.kassper.controller;

import com.kassper.model.bd;
import com.kassper.model.person;
import com.kassper.model.setting;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@Controller
@RequestMapping
public class MainController {

    private static String text;
    private static int id;
    private static int status;

    @GetMapping("/Password")
    public String seyInPassGet(Model model) {
        text = "Enter your username and password to authenticate the user.                 ";
        model.addAttribute("textic", text);
        return "Password";}

    @GetMapping("/Start")
    public String seyInStartGet(){return "StartPosition";}

    @PostMapping("/Start")
    public String seyInStartPost(@RequestParam("uname") String login, @RequestParam("psw") String password, Model model) {
        try {
            person.passLog(login,password);
            String FIO=person.getSurname()+" "+person.getName()+" "+person.getMiddleName();
            if(person.getLogin() !=null || person.getPassword()!=null){
                MainController.id = person.getId();
                MainController.status = person.getStatus();
                return "redirect:/Start";
            }
            else {
                text = "Wrong login or password. Try again.               ";
                model.addAttribute("textic", text);
                return "Password";
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        text = "Any problems? Contact your administrator..               ";
        model.addAttribute("textic", text);
        return "Password";
    }

    @GetMapping("/Setting")
    public String seyInSettingPost(Model model){
        model.addAttribute("m",setting.allPosition("predmet"));
        model.addAttribute("n", setting.allPosition("status"));
        return "SettingsView";}

    @PostMapping("/Setting")
    public String seyAddSettingStatus (@RequestParam ("ID") String id, @RequestParam ("Name") String name, @RequestParam ("but") String button, Model model){
        if ( button.equals("add1")) setting.addSeiing(id, name, "predmet");
        if ( button.equals("add2")) setting.addSeiing(id, name, "status");
        if ( button.equals("dell1")) setting.dellSetting(id, name, "predmet");
        if ( button.equals("dell2")) setting.dellSetting(id, name, "status");
        model.addAttribute("m",setting.allPosition("predmet"));
        model.addAttribute("n", setting.allPosition("status"));
        return "redirect:/Setting";
    }

    @GetMapping("/Person")
    public String seyInPersonPost(Model model){
        model.addAttribute("options",setting.allPositionOnPersonView("status"));
        return "PersonView";
    }

    @PostMapping("/Person")
    public String seyInPerson(@RequestParam("surname") String surname,
                              @RequestParam("name") String name,
                              @RequestParam("middlename") String middlename,
                              @RequestParam("login") String loginPers,
                              @RequestParam("psw") String passPerson,
                              @RequestParam("dataYers") Date dataYers,
                              @RequestParam("options") String status){
        System.out.println("1");
        person.addPerson(surname,name,middlename,loginPers,passPerson,dataYers);
        System.out.println("2");
        return "redirect:/Person";
    }

    @GetMapping("/JournalList")
    public String seyJournalListGet (Model model){
        model.addAttribute("dataJournalAndPredmet","Journal list for __.__.____. Group - ______;");
        return "JournalView";
    }

    @PostMapping("/JournalList")
    public String seyJournalListPost(){
        return "redict:/JournalList";
    }
}
