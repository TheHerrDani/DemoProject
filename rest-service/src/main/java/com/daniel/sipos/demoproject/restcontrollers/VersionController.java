package com.daniel.sipos.demoproject.restcontrollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.info.BuildProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/version")
public class VersionController {
  @Autowired
  BuildProperties buildProperties;

  @GetMapping(path = "/find")
  public @ResponseBody
  String findVersion() {
    return buildProperties.getVersion();
  }
}
