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
public class Question2EventSubscriber implements StatementSubscriber {

    /** Logger */
    private static Logger LOG = LoggerFactory.getLogger(Question2EventSubscriber.class);
    String nome;
    /**
     * {@inheritDoc}
     */
    
    

    public String getQuestion2(String parametro, String t, String nome) {
    this.nome = nome;
          
        return "select * from TemperatureEvent " +
               "match_recognize ( " +
               "measures (count(A.fc)/60) as T " +
               "pattern (A{"+t+", })  interval  90 days or terminated " +
//"interval 10 seconds " +
               "define " +
//"A as A.fc>"+fc+") " ;
        "A as "+parametro+")  " ;
          
             
    
    }

    /**
     * Listener method called when Esper has detected a pattern match.
     */
    public void update(Map<String, Double> eventMap) {

        Conexao C = new Conexao();
        C.Conect();
        
        // average temp over 10 secs
        Double avg = (Double) eventMap.get("T");
        C.InserirResultado(nome+"b", (avg*60));
        
        StringBuilder sb = new StringBuilder();
        sb.append("---------------------------------");
       // sb.append("\n-  Pergunta 1a = " + avg);
        sb.append("\n-  "+nome+"b Ativada "+ (avg*60));
        sb.append("\n---------------------------------");

        LOG.debug(sb.toString());
    }

    @Override
    public String getQuestion2(String fc, String t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getStatement(String parametro, String t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
}
