package fr.epsi.jeeProject.jmx;

import java.lang.management.ManagementFactory;

import javax.management.InstanceAlreadyExistsException;
import javax.management.MBeanRegistrationException;
import javax.management.MBeanServer;
import javax.management.MalformedObjectNameException;
import javax.management.NotCompliantMBeanException;
import javax.management.ObjectName;

public class LancerAgent {
    public static void main(String[] args) {
        MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
        ObjectName name;
        try {
            name = new ObjectName("fr.espi.jeeProject.jmx:type=LogMBean");
            Log log = new Log();
            mbs.registerMBean(log, name);

            name = new ObjectName("fr.espi.jeeProject.jmx:type=PostMBean");
            Post post = new Post();
            mbs.registerMBean(post, name);

            System.out.println("Lancement ...");
            while (true) {
                Thread.sleep(1000);
            }
        } catch (MalformedObjectNameException | NullPointerException | InstanceAlreadyExistsException | MBeanRegistrationException | NotCompliantMBeanException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}