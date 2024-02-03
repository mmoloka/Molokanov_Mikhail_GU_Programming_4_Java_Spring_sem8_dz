package org.example.sem8_dz;

import org.example.sem8_dz.aspect.RecoverException;
import org.example.sem8_dz.aspect.Timer;
import org.slf4j.event.Level;
import org.springframework.stereotype.Component;
import org.example.sem8_dz.aspect.Loggable;

@Component
public class Louiggi implements Brother {

    // @Loggable(level = Level.WARN)
    public void method1(String arg1, int arg2) {

    }

    //@Loggable(level = Level.WARN)
    public String method2() {
        return "value";
    }

    @RecoverException(noRecoverFor = {RuntimeException.class})
    public String method3() {
        throw new NullPointerException("nullpointerexceptionmsg");
    }

    @Timer
    public void method4() throws InterruptedException {
        Thread.sleep(1020);
    }

}
