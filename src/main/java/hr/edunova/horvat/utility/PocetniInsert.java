
package hr.edunova.horvat.utility;

import com.github.javafaker.Faker;
import hr.edunova.horvat.controller.ObradaOperater;
import hr.edunova.horvat.model.Grupa;
import hr.edunova.horvat.model.Operater;
import hr.edunova.horvat.model.Polaznik;
import hr.edunova.horvat.model.Predavac;
import hr.edunova.horvat.model.Smjer;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.Session;
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author Josip
 */
public class PocetniInsert {
    
    public static void izvedi(){
        
        Session session = HibernateUtil.getSessionFactory().openSession();
        
        Operater operater = new Operater();
        operater.setIme("Josip");
        operater.setPrezime("Horvat");
        operater.setUloga("oper");
        operater.setEmail("josiph988@gmail.com");
        operater.setLozinka(BCrypt.hashpw("j", BCrypt.gensalt()));
        
        ObradaOperater oo = new ObradaOperater();
        oo.setEntitet(operater);
        
        try {
            oo.create();
        } catch (EdunovaException ex) {
          ex.printStackTrace();      
        }
        
        
   Smjer java = kreirajSmjer("Java programiranje", "Uce se java, sql itd...", new BigDecimal(4999.99), true);
   Smjer php = kreirajSmjer("PHP programiranje", "Loren ipsium", new BigDecimal(4000), false);
   
   session.beginTransaction();
   session.save(java);
   session.save(php);
   
   
   Faker faker = new Faker();
   
//   String[] oibi = {"44879378548","38714462960","48653367511",
//            "07463739447","55376858772","57121746664","45088797644","97067197029",
//            "36388448333","13633152331"};
   
        Predavac p , predavacJava=null, predavacPhp=null;
        
   for(int i=0; i<10; i++){
       p = new Predavac();
       p.setIme(faker.name().firstName());
       p.setPrezime(faker.name().lastName());
       p.setOib(Oib.getOibItCentrala());
       p.setIban(faker.finance().iban("HR"));
       p.setEmail(p.getIme().toLowerCase()+"."+p.getPrezime()+"@edunova.hr");
       session.save(p);
       
       if(i==0){
           predavacJava=p;
       }
       if(i==1){
           predavacPhp=p;
       }
   }
   Polaznik polaznik;
        List<Polaznik> polazniciJava = new ArrayList<>();
         List<Polaznik> polazniciPHP = new ArrayList<>();
        for(int i=0;i<100;i++){
            
            polaznik=new Polaznik();
            polaznik.setIme(faker.name().firstName());
            polaznik.setPrezime(faker.name().lastName());
            polaznik.setOib(Oib.getOibItCentrala());
            
           
            polaznik.setBrojUgovora("Ugovor_" + (i+1));
            polaznik.setEmail(polaznik.getIme().toLowerCase()+"."+polaznik.getPrezime().toLowerCase()+"@edunova.hr");
            session.save(polaznik);
           if(i<20){
               polazniciJava.add(polaznik);
           }
           if(i>20 && i<40){
               polazniciPHP.add(polaznik);
           }
        }
        
        session.getTransaction().commit();
        
        
        
        session.beginTransaction();
        Grupa jp22 = new Grupa();
        jp22.setNaziv("JP22");
        jp22.setDatumpocetka(new Date());
        jp22.setSmjer(java);
        jp22.setPredavac(predavacJava);
        jp22.setPolaznici(polazniciJava);
        session.save(jp22);
        
        Grupa pp21 = new Grupa();
        pp21.setNaziv("PP21");
        pp21.setDatumpocetka(new Date());
        pp21.setSmjer(php);
        pp21.setPredavac(predavacPhp);
        pp21.setPolaznici(polazniciPHP);
        session.save(pp21);
        
        
        session.getTransaction().commit();
        
        
        
    }
//    public static void adminOperater(){
//        Operater operater = new Operater();
//        operater.setIme("Edunova");
//    }
    
    
    private static Smjer kreirajSmjer(String naziv, String opis, BigDecimal cijena, Boolean verificiran){
      
        Smjer smjer = new Smjer();
    smjer.setNaziv(naziv);
    smjer.setOpis(opis);
    smjer.setCijena(cijena);
    smjer.setVerificiran(verificiran);
    
    return smjer;
    }

    public static void adminOperater() {
        
        
        Operater operater = new Operater();
        operater.setIme("Big");
        operater.setPrezime("Boss");
        operater.setUloga("Admin");
        operater.setEmail("bossbigg@gmail.com");
        operater.setOib(Oib.getOibItCentrala());
        operater.setLozinka(BCrypt.hashpw("b", BCrypt.gensalt()));
        
        ObradaOperater oo = new ObradaOperater();
        oo.setEntitet(operater);
        
        try {
            oo.create();
        } catch (EdunovaException ex) {
          ex.printStackTrace();      
        }
        
        
        
    }

    
}
