package com.cor.cep.subscriber;

import com.database.data.Conexao;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Wraps Esper Statement and Listener. No dependency on Esper libraries.
 */
@Component
public class MonitorEventSubscriber implements StatementSubscriber {

    public MonitorEventSubscriber( ){ }
    public MonitorEventSubscriber(String nome){this.nome=nome;}
 /**    Logger */
    private static Logger LOG = LoggerFactory.getLogger(MonitorEventSubscriber.class);
    String nome;
    /**
     * {@inheritDoc}
     */
    
    

    public String getStatement(String parametro, String t) {
    this.nome = nome;
         
        return "select *  from TemperatureEvent " +
               "match_recognize ( " +
               "measures A as A " +
               "pattern (A{"+t+"})  " +
//"interval 10 seconds " +
               "define " +
//"A as A.fc>"+fc+") " ;
        "A as "+parametro+") " ;
          
       //tempo total de atividades por dia (ttad)
       //total de dias por semana de atividades por determinado periodo continuo
     
    }

    /**
     * Listener method called when Esper has detected a pattern match.
     */
    public void update(Map<String, Double> eventMap) {

          Conexao C = new Conexao();
          C.Conect();
            
        // average temp over 10 secs
        Double avg = (Double) eventMap.get("fc");
        C.InserirResultado(nome+"a", avg);
        
        StringBuilder sb = new StringBuilder();
        sb.append("---------------------------------");
       // sb.append("\n-  Pergunta 1a = " + avg);
        sb.append("\n-  "+nome+"a Ativada ");
        sb.append("\n---------------------------------");

        LOG.debug(sb.toString());
    }



   

    
}
