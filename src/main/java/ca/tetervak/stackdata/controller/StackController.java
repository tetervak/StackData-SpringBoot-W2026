package ca.tetervak.stackdata.controller;

import ca.tetervak.stackdata.domain.StackData;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@Slf4j
public class StackController {

    @GetMapping("/")
    public String redirectToStack() {
        return "redirect:stack";
    }

    @GetMapping( "/stack")
    public String displayStack(
            HttpSession session,
            @RequestParam(defaultValue = "") String popped,
            Model model
    ) {
        log.trace("displayStack() is called");
        log.debug("popped = {}", (popped.isEmpty() ? "empty" : popped));
        StackData stack = getStackData(session);
        model.addAttribute("items", stack.getItems());
        model.addAttribute("popped", popped);
        return "stack-data";
    }

    private StackData getStackData(HttpSession session) {
        log.trace("getStackData() is called");
        StackData stack = (StackData) session.getAttribute("stack");
        if(stack == null){
            log.trace("StackData is not found in the Session; making new StackData");
            stack = new StackData();
            session.setAttribute("stack", stack);
        }else{
            log.trace("Previous StackData is found in the Session");
        }
        return stack;
    }

    @GetMapping("/process")
    public String processInput(
            @RequestParam String todo,
            @RequestParam(defaultValue = "") String pushed,
            HttpSession session,
            Model model
    ) {
        log.trace("processInput() is called");
        log.debug("todo = {}", todo);
        StackData stack = getStackData(session);
        if (todo.equals("Push")) {
            if (!pushed.trim().isEmpty()) {
                stack.push(pushed);
                log.debug("the value [{}] is pushed", pushed);
            }
        } else if (todo.equals("Pop")) {
            if (!stack.isEmpty()) {
                String popped = stack.pop();
                log.debug("the value [{}] is popped", popped);
                model.addAttribute("popped", popped);
            }
        }
        model.addAttribute("items", stack.getItems());
        return "stack-data";
    }
}
