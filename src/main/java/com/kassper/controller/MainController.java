package com.kassper.controller;

import com.kassper.model.bd;
import com.kassper.model.person;
import com.kassper.model.setting;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@Controller
public class MainController {

    private static String text;
    private static int id;
    private static int status;

    @GetMapping("/Password")
    public String seyInPass(Model model) {
        text = "Enter your username and password to authenticate the user.                 ";
        model.addAttribute("textic", text);
        return "Password";}

    @PostMapping("/Start")
    public String seyInStart(@RequestParam("uname") String login, @RequestParam("psw") String password, Model model) {
        try {
            person.passLog(login,password);
            String FIO=person.getSurname()+" "+person.getName()+" "+person.getMiddleName();
            if(person.getLogin() !=null || person.getPassword()!=null){
                MainController.id = person.getId();
                MainController.status = person.getStatus();
                model.addAttribute("my", FIO);
                return "StartPosition";
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

    @GetMapping("/Person")
    public String seyInPersonGet (Model model){
        model.addAttribute("options",setting.allPosition("status"));
        return "PersonView";
    }

    @PostMapping("/Setting")
    public String seyInSettingGet( Model model){
        model.addAttribute("m",setting.allPosition("predmet"));
        model.addAttribute("n", setting.allPosition("status"));
        return "SettingsView";}






    @PostMapping("/Person")
    public String seyInPerson(@RequestParam("surname") String surname,
                              @RequestParam("name") String name,
                              @RequestParam("middlename") String middlename,
                              @RequestParam("login") String loginPers,
                              @RequestParam("psw") String passPerson,
                              @RequestParam("dataYers") Date dataYers,
                              @RequestParam("status") String status){
        person.addPerson(surname,name,middlename,loginPers,passPerson,dataYers,status.toString());
        return "PersonView";}




   // @GetMapping("/Setting")
    @GetMapping(value = "/Setting/s")
    public String seyAddSettingStatus (@RequestParam ("statusID") String idStatus, @RequestParam ("statusName") String nameStatus, Model model){
        setting.addSeiing(idStatus, nameStatus, "status");
        //seyInSettingGet(model);
        return "SettingsView";
    }
   // @GetMapping("/Setting")
    @GetMapping(value = "/Setting/c")
    public  String seyAddSettingClass (@RequestParam ("classID") String idClass, @RequestParam ("className") String nameClass,Model model){
        setting.addSeiing(idClass, nameClass, "predmet");
        return "SettingsView";
    }
}