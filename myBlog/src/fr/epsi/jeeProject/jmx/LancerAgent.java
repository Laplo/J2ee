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
        ObjectName name = null;
        try {
            name = new ObjectName("com.jmdoudoux.tests.jmx:type=PremierMBean");
            Premier mbean = new Premier();
            mbs.registerMBean(mbean, name);
            System.out.println("Lancement ...");
            while (true) {
                Thread.sleep(1000);
                mbean.setValeur(mbean.getValeur() + 1);
            }
        } catch (MalformedObjectNameException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            e.printStackTrace();
        } catch (InstanceAlreadyExistsException e) {
            e.printStackTrace();
        } catch (MBeanRegistrationException e) {
            e.printStackTrace();
        } catch (NotCompliantMBeanException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
        }
    }
}