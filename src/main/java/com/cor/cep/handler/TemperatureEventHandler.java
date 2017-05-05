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

    @Autowired
    @Qualifier("monitorEventSubscriber")
    private MonitorEventSubscriber monitorEventSubscriber;
   
   @Autowired
    @Qualifier("question2EventSubscriber")
    private Question2EventSubscriber question2EventSubscriber;

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
       String dados[] = new String [5];
       dados = C.Questionario();
       
       String parametro = C.Parametros();
               
       LOG.debug("create Timed Average Monitor");
       monitorEventStatement = epService.getEPAdministrator().createEPL(monitorEventSubscriber.getStatement(parametro, dados[2], dados[0]));
       monitorEventStatement.setSubscriber(monitorEventSubscriber);
    }
    
    
      private void Question2MonitorExpression() {
         
        
       Conexao C = new Conexao();
       C.Conect();
       String dados[] = new String [5];
       dados = C.Questionario();
       
       String parametro = C.Parametros();
     LOG.debug("create Timed Average Monitor");
     question2EventStatement = epService.getEPAdministrator().createEPL(question2EventSubscriber.                getQuestion2(parametro, dados[2], dados[0]));       question2EventStatement.setSubscriber(                  question2EventSubscriber);
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
