package com.cor.cep.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.cor.cep.event.TemperatureEvent;
import com.cor.cep.subscriber.MonitorEventSubscriber;
import com.cor.cep.subscriber.Question2EventSubscriber;
import com.cor.cep.subscriber.StatementSubscriber;
import com.database.data.Conexao;
import com.espertech.esper.client.Configuration;
import com.espertech.esper.client.EPServiceProvider;
import com.espertech.esper.client.EPServiceProviderManager;
import com.espertech.esper.client.EPStatement;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * This class handles incoming Temperature Events. It processes them through the EPService, to which
 * it has attached the 3 queries.
 */
@Component
@Scope(value = "singleton")
public class TemperatureEventHandler implements InitializingBean{

    /** Logger */
    private static Logger LOG = LoggerFactory.getLogger(TemperatureEventHandler.class);

    /** Esper service */
    private EPServiceProvider epService;
    private EPStatement monitorEventStatement;
    private EPStatement question2EventStatement;
/**
    @Autowired
    @Qualifier("monitorEventSubscriber")
    private MonitorEventSubscriber monitorEventSubscriber;
  
   @Autowired
    @Qualifier("question2EventSubscriber")
    private Question2EventSubscriber question2EventSubscriber;
 */
   /** 
     * Configure Esper Statement(s).
     */
    public void initService() {

        LOG.debug("Initializing Servcie ..");
        Configuration config = new Configuration();
        config.addEventTypeAutoName("com.cor.cep.event");
        epService = EPServiceProviderManager.getDefaultProvider(config);

        createTemperatureMonitorExpression();
        Question2MonitorExpression();

    }

    /**
     * EPL to monitor the average temperature every 10 seconds. Will call listener on every event.
     */
    private void createTemperatureMonitorExpression() {
         
        
       Conexao C = new Conexao();
       C.Conect();
       String[]   r_valores = new String[20];
       r_valores = C.QdtRegra_Id();
       String dados[] = new String [20];
       List <MonitorEventSubscriber> mes = new ArrayList<MonitorEventSubscriber>() ;
       
      for(int i=0; i<=C.QdtRegra()-1; i++){
          
       
       dados = C.Questionario(r_valores[i]);
       String parametro = C.Parametros(r_valores[i]);
        
      LOG.debug("Criação da regra  "+dados[0]+"a");
   mes.add(new MonitorEventSubscriber(dados[0]));
   monitorEventStatement = epService.getEPAdministrator().createEPL(mes.get(i).getStatement(parametro, dados[2]));   
   monitorEventStatement.setSubscriber(mes.get(i));
     
       }
    
    
    }
    
   
      private void Question2MonitorExpression() {
         
        
       Conexao C = new Conexao();
       C.Conect();
       String[]   r_valores = new String[20];
       r_valores = C.QdtRegra_Id();
       String dados[] = new String [20];
       
        List <Question2EventSubscriber> qes = new ArrayList<Question2EventSubscriber>() ;
       
      for(int i=0; i<=C.QdtRegra()-1; i++){
          
       
       dados = C.Questionario(r_valores[i]);
       String parametro = C.Parametros(r_valores[i]);
     LOG.debug("Criação da regra  "+dados[0]+"b");
     
     qes.add(new Question2EventSubscriber(dados[0]));
     question2EventStatement = epService.getEPAdministrator().createEPL(qes.get(i).getQuestion2(parametro, dados[2]));       
     question2EventStatement.setSubscriber(qes.get(i));
    }
      }
    
  /**   
     * Handle the incoming TemperatureEvent.
     */
    public void handle(TemperatureEvent event) {

        LOG.debug(event.toString());
        epService.getEPRuntime().sendEvent(event);

    }

    @Override
    public void afterPropertiesSet() {
        
        LOG.debug("Configuring..");
        initService();
    }
}
