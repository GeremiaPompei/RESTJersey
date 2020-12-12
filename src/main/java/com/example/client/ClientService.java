package com.example.client;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;

import com.example.model.RestRequest;
import com.example.model.RestResponse;

import java.util.concurrent.Callable;

/**
 * Classe utile a creare oggetti client che permettono di inviare richieste rest.
 *
 * @author geremiapompei
 */
public class ClientService implements Callable<ClientService> {
    /**
     * Indirizzo del mittente.
     */
    private String senderAddress;

    /**
     * Metodo costruttore.
     *
     * @param address Indirizzo che permette di inizializzare la rispettiva variabile di istanza.
     */
    public ClientService(String address) {
        this.senderAddress = address;
    }

    /**
     * Metodo per eseguire una richiesta post.
     *
     * @param restRequest Richiesta rest inviata.
     * @return Risposta computata.
     */
    public RestResponse postRequest(RestRequest restRequest) {
        Client c = ClientBuilder.newClient();
        WebTarget target = c.target(restRequest.getReceiver()).path("api");
        Response response = target.request().post(Entity.json(restRequest));
        return response.readEntity(RestResponse.class);
    }

    /**
     * Metodo sovrascritto utile per far diventare tale client eseguibile in un thread e parallelizzarlo rispetto ad
     * altre unità computazionali.
     *
     * @return Tale client per tenerne traccia.
     */
    @Override
    public ClientService call() {
        return this;
    }
}