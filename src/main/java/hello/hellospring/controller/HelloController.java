package hello.hellospring.controller;

import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {
   @GetMapping
    public String hello(Model model){
        model.addAttribute("data","hello!");
        return "hello";
   }

   @GetMapping("hello-mvc") //mvc는 View를 찾아서 View에 데이터 전달
    public String helloMvc(@RequestParam("name")String name, Model model){
       model.addAttribute("name",name);
        return "hello-template";
   }

   @GetMapping("hello-string")
   @ResponseBody //http에서 바디에 데이터를 직접 넣겠다. /주로 데이터 전달에 많이 쓰임
    public String helloString(@RequestParam("name") String name){
       return "hello "+ name;
   }

   @GetMapping("hello-api")  //json 방식
    @ResponseBody
   public Hello helloApi(@RequestParam("name") String name){
       Hello hello = new Hello();
        hello.setName(name);
       return hello;
   }
    static class Hello{
       private String name; //key

       public String getName() {
           return name;
       }

       public void setName(String name) {
           this.name = name;
       }
   }
}
