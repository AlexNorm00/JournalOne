package com.kassper.controller;

import com.kassper.model.Journal;
import com.kassper.model.bd;
import com.kassper.model.person;
import com.kassper.model.setting;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Calendar;
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
    public String seyInPerson(@RequestParam("surname") String surname, @RequestParam("name") String name, @RequestParam("middlename") String middleName,
                              @RequestParam("login") String loginPers, @RequestParam("psw") String passPerson, @RequestParam("dataYers") String dataYers,
                              @RequestParam("select") String status, @RequestParam("nomGroup") int nomGroup){
        person.addPerson(surname,name,middleName,loginPers,passPerson,dataYers,status,nomGroup);
        return "redirect:/Person";
    }


    private static int group;
    private static Date date = new Date();
    private static String predmet;
    private static String dateFormat1 = new SimpleDateFormat("dd.MM.yyyy").format(date);
    private static final String dateFormat2 = new SimpleDateFormat("yyyy-MM-dd").format(date);
    private static String TableViev;

    @GetMapping("/JournalList")
    public String seyJournalListGet (Model model){
        model.addAttribute("dataJournalAndPredmet","Journal list for "+dateFormat1+". Group - "+group);
        model.addAttribute("options", setting.allPositionOnPersonView("predmet"));
        model.addAttribute("n", TableViev);
        model.addAttribute("date",dateFormat2);


        return "JournalView";
    }

    @PostMapping("/JournalList")
    public String seyJournalListPost(@RequestParam(value = "dateLanguage", required = false) String dataYers,
                                     @RequestParam(value = "NomGroup", required = false) int NomGroup,
                                     @RequestParam(value = "but") String allButton,
                                     @RequestParam(value = "lessonTopic", required = false) String lessonTopic,
                                     @RequestParam("select") String select, Model model){
        group = NomGroup;
        predmet = select;
        if (allButton.equals("searchButton")) {
            if (NomGroup > 0) {
                TableViev=Journal.allPositionJournalView(group,dataYers,predmet);
            }
        }
        if (allButton.equals("createLesson")){
            if (NomGroup > 0 && !select.equals("all subject"))
            TableViev = Journal.createJournal(group,dataYers,predmet);
        }
        if (allButton.equals("сloseLesson")){

        }


        dateFormat1 = String.join(".",dataYers.substring(8),dataYers.substring(5,7),dataYers.substring(0,4));

        return "redirect:/JournalList";
    }
}
