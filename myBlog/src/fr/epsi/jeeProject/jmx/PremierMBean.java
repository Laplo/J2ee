package fr.epsi.jeeProject.jmx;

public interface PremierMBean {
    String getNom();
    int getValeur();
    void setValeur(int valeur);
    void rafraichir();
}
