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


    @PostMapping("/Start")
    public String seyInStartPost(@RequestParam("uname") String login, @RequestParam("psw") String password, Model model) {
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
    public String seyInPersonPost(Model model){
        model.addAttribute("options",setting.allPosition("status"));
        return "PersonView";
    }

    @GetMapping("/Setting")
    public String seyInSettingPost(Model model){
        model.addAttribute("m",setting.allPosition("predmet"));
        model.addAttribute("n", setting.allPosition("status"));
        return "SettingsView";}

/*    @GetMapping("/Setting")
    public String seyInSettingGet(Model model){
        model.addAttribute("m",setting.allPosition("predmet"));
        model.addAttribute("n", setting.allPosition("status"));
        return "SettingsView";}*/



    @PostMapping("/Person")
    public String seyInPerson(@RequestParam("surname") String surname,
                              @RequestParam("name") String name,
                              @RequestParam("middlename") String middlename,
                              @RequestParam("login") String loginPers,
                              @RequestParam("psw") String passPerson,
                              @RequestParam("dataYers") Date dataYers,
                              @RequestParam("status") String status){
        person.addPerson(surname,name,middlename,loginPers,passPerson,dataYers,status.toString());
        return "Start";}


/*    // @GetMapping("/Setting")
    @PostMapping(value = "/Setting)
    public String seyAddSettingClass (@RequestParam ("classID") String idClass, @RequestParam ("className") String nameClass){;
        setting.addSeiing(idClass, nameClass, "predmet");
        return "SettingsView";
    }*/

   // @GetMapping("/Setting")
    @PostMapping(value = "/Setting")
    public String seyAddSettingStatus (@RequestParam ("ID") String id, @RequestParam ("Name") String name, @RequestParam ("but") String button){
        String settingParsms = button == "add2"? "status" : button == "add1" ? "predmet " : null;
        if (settingParsms==null) {
            settingParsms = button=="dell2" ? "status" : "predmet";
            setting.dellSetting(id,name,settingParsms);
        }
        else setting.addSeiing(id, name, settingParsms);
        return "SettingsView";
    }

}