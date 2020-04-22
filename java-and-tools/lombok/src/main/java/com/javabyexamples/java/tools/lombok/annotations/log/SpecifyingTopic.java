package com.javabyexamples.java.tools.lombok.annotations.log;

import lombok.extern.apachecommons.CommonsLog;
import lombok.extern.java.Log;
import lombok.extern.jbosslog.JBossLog;
import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import lombok.extern.slf4j.XSlf4j;

@CommonsLog(topic = "integration")
@JBossLog(topic = "integration")
@Log4j(topic = "integration")
@Log4j2(topic = "integration")
@Log(topic = "integration")
@Slf4j(topic = "integration")
@XSlf4j(topic = "integration")
public class SpecifyingTopic {
    public static void main(String[] args) {
        log.error("Error occurred");
    }
}
