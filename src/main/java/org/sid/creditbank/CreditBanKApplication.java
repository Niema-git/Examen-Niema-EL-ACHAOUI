package org.sid.creditbank;

import org.sid.creditbank.entities.*;
import org.sid.creditbank.enums.StatutCredit;
import org.sid.creditbank.enums.TypeBien;
import org.sid.creditbank.enums.TypeRemboursement;
import org.sid.creditbank.repositories.ClientRepository;
import org.sid.creditbank.repositories.CreditRepository;
import org.sid.creditbank.repositories.RemboursementRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.List;

@SpringBootApplication
public class CreditBanKApplication {

    public static void main(String[] args) {
        SpringApplication.run(CreditBanKApplication.class, args);
    }

    @Bean
    CommandLineRunner start(ClientRepository clientRepository,
                            CreditRepository creditRepository,
                            RemboursementRepository remboursementRepository) {
        return args -> {

            // 1. Créer quelques clients
            List<Client> clients = List.of(
                    new Client(null, "Niema ELACHAOUI", "niema@gmail.com", null),
                    new Client(null, "Sara fatahi", "sara@gmail.com", null),
                    new Client(null, "Youssef hadri", "youssef@gmail.com", null)
            );
            clientRepository.saveAll(clients);

            // credit personnel
            CreditPersonnel cp = new CreditPersonnel();
            cp.setClient(clients.get(0));
            cp.setDateDemande(new Date());
            cp.setStatut(StatutCredit.ACCEPTE);
            cp.setDateAcception(new Date());
            cp.setMontant(50000);
            cp.setDuree(24);
            cp.setTauxInteret(4.5);
            cp.setMotif("Achat de voiture");
            creditRepository.save(cp);

            // credit immobilier
            CreditImmobilier ci = new CreditImmobilier();
            ci.setClient(clients.get(1));
            ci.setDateDemande(new Date());
            ci.setStatut(StatutCredit.EN_COURS);
            ci.setMontant(800000);
            ci.setDuree(240);

            ci.setTypeBien(TypeBien.APPARTEMENT);
            creditRepository.save(ci);

            //  credit professionnel
            CreditProfessionnel cpr = new CreditProfessionnel();
            cpr.setClient(clients.get(2));
            cpr.setDateDemande(new Date());
            cpr.setStatut(StatutCredit.REJETE);
            cpr.setMontant(200000);
            cpr.setDuree(60);
            cpr.setTauxInteret(5.1);
            cpr.setMotif("Achat équipement");
            cpr.setRaisonSociale("Kadiri SARL");
            creditRepository.save(cpr);

            //  remboursements
            List<Remboursement> remboursements = List.of(
                    new Remboursement(null, new Date(), 2200, TypeRemboursement.MENSUALITE, cp),
                    new Remboursement(null, new Date(), 2200, TypeRemboursement.MENSUALITE, cp),
                    new Remboursement(null, new Date(), 10000, TypeRemboursement.REMBOURSEMENT_ANTICIPE, cp)
            );
            remboursementRepository.saveAll(remboursements);

            //  Afficher les crédits par client
            System.out.println(" Crédits niema ");
            creditRepository.findByClientId(clients.get(0).getId())
                    .forEach(c -> System.out.println(c.getId() + " | " + c.getClass().getSimpleName()
                            + " | " + c.getMontant() + " DH | " + c.getStatut()));

            // Afficher les remboursements du crédit personnel
            System.out.println("Remboursements crédit #" + cp.getId() );
            remboursementRepository.findByCreditId(cp.getId())
                    .forEach(r -> System.out.println(r.getType() + " | " + r.getMontant() + " DH"));
        };
    }
}
