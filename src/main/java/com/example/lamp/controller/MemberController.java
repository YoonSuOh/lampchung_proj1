package com.example.lamp.controller;

import com.example.lamp.domain.Member;
import com.example.lamp.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
public class MemberController {
    private final MemberService service;
    private final Logger LOG = LoggerFactory.getLogger(MemberController.class.getName());

    @GetMapping("/login")
    public String getlogin(HttpServletRequest req){
        String referer = req.getHeader("Referer");
        LOG.info(referer);
        req.getSession().setAttribute("referer", referer);
        return "login";
    }

    @PostMapping("/login")
    public String login(HttpServletRequest req, Member member){
        HttpSession session = req.getSession();
        Member login = service.login(member);
        String referer = (String) session.getAttribute("referer");
        LOG.info(referer);

        if (login == null) {
            session.setAttribute("member", null);
            LOG.info("login failed......");
            return "redirect:/login";
        } else {
            LOG.info("login success!");
            session.setAttribute("member", login);  
            session.setAttribute("name", login.getName());
            session.setAttribute("password", login.getPassword());
            LOG.info(String.valueOf((login.getName())));
            LOG.info(String.valueOf((login.getPassword())));

            if (referer != null && !referer.contains("login")) {
                LOG.info("prev page is checked, return to check page.");
                return "redirect:" + referer;
            } else {
                LOG.info("prev page is not found, return to main page.");
                return "redirect:/";
            }
        }
    }

    @GetMapping(value="/logout")
    public String logout(HttpServletRequest req, HttpSession session){
        String referer = req.getHeader("referer");
        session.invalidate();
        LOG.info("logout success!");
        return "redirect:" + referer;
    }
}
