/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.database.data;


import java.sql.*;


public class Conexao {
	
	private Connection connection = null;
	private Statement statement = null;
	private ResultSet resultSet = null;
	      String[] query = null;
	
	
	public void Conect(){
		
        String servidor = "jdbc:mysql://localhost:3306/questionario";
        String usuario = "root";
        String senha = "nxj1303";
        String driver = "com.mysql.jdbc.Driver";
        
        try{
        	
        	Class.forName(driver);
        	this.connection =  (Connection) DriverManager.getConnection(servidor, usuario, senha);
        	this.statement = (Statement) this.connection.createStatement();
        	
        	System.out.println("Banco Conectado!");
        	
        }catch(Exception e){
        	
        	System.out.println("Errorrrr:" + e.getMessage());
        	
        	
        }
        
     
		
		
	}
	
	
	
        
        
        
      
    
        
        
        
        
        
        
        
        
        
        
        
        
        
    
    
    
    public void InserirResultado(String exibir, Double tempo){
    	
    	com.mysql.jdbc.PreparedStatement st = null;
    
      try {
    	 String sql = "INSERT INTO `resultado` (`rs_id`, `rs_regra`, `rs_tempo_total`, `rs_data`) VALUES (NULL,?, ?, NOW())";
    	     st = (com.mysql.jdbc.PreparedStatement) connection.prepareStatement(sql);    	     
    	     st.setString(1, ""+exibir);
             st.setString(2, ""+tempo);
    	     st.execute();
    	     System.out.println("Inserido com sucesso");
    	     connection.close();
    	}

    	catch (SQLException ex) {
    	   System.out.println("ERROR!" + ex.getMessage());
    	 }
    	
    }
    
    
    
    
    
    
    
    
    public int QdtRegra(){
     
     String nome="", id="";
     String parametros[] = new String [5];
      int  rowcount = 0;
    try{
      	
     String query5 = "SELECT rg_id as ID, rg_nome as NOME FROM `regras`";
     
     this.resultSet = this.statement.executeQuery(query5);
     this.statement = (Statement) this.connection.createStatement();
     
   
    
     rowcount = this.resultSet.last() ? this.resultSet.getRow() : 0;
    
      
    }catch(SQLException e){
      	System.out.println("Errorrr:" + e.getMessage());
      }
        
   
    
     return rowcount;   
      	
      }
    
    
    
    
    
    
    public String[] QdtRegra_Id(){
     
     String id="";
     String parametros[] = new String [5];
      
    try{
      	
     String query5 = "SELECT rg_id as ID, rg_nome as NOME FROM `regras`";
     
     this.resultSet = this.statement.executeQuery(query5);
     this.statement = (Statement) this.connection.createStatement();
      
         
      int i = 0; 
     while(this.resultSet.next()){
      	
       id   = this.resultSet.getString("ID");
      
      this.query[i++] = id;
    }
    
    
      
    }catch(SQLException e){
      	System.out.println("Errorrr:" + e.getMessage());
      }
      
       
     
     return this.query;   
      	
      }
    
    
    
    
    
    
    
    
    
    
    
       
    
    
    
     public String[] Questionario(){
     
     String nome="", contexto="", tempo="", operador="", valor="", query="";
     String parametros[] = new String [5];
    try{
      	
     String query5 = "SELECT rg.rg_nome as Nome, rg.rg_duracao as Tempo, ct.ct_abreviacao as Contexto, cd.cd_operador as Operador, cd.cd_valor as Valor FROM `regras` AS rg INNER JOIN condicoes as cd ON cd.cd_regra = rg.rg_id INNER JOIN contexto AS ct ON ct.ct_id = cd.cd_contexto";
     
     this.resultSet = this.statement.executeQuery(query5);
     this.statement = (Statement) this.connection.createStatement();
     
     while(this.resultSet.next()){
      	
       nome      = this.resultSet.getString("Nome");
       contexto  = this.resultSet.getString("Contexto");
       tempo     = this.resultSet.getString("Tempo");
       operador  = this.resultSet.getString("Operador");
       valor     = this.resultSet.getString("Valor");
      //  query += ""+contexto+" "+operador+" "+valor+" "+conector+" ";
    }
    
    
      
    }catch(SQLException e){
      	System.out.println("Errorrr:" + e.getMessage());
      }
        
     parametros[0]= nome;
     parametros[1]= contexto;
     parametros[2]= tempo;
     parametros[3]= operador; 
     parametros[4]= valor; 
     return parametros;   
      	
      }
     
     
     
     
     
     
     
     
     
     
     
     
     
     public String Parametros(){
     
     String nome="", contexto="", tempo="", operador="", valor="", query="", conector="";
     String teste="";
    try{
      	
     String query5 = "SELECT  ct.ct_abreviacao as Contexto, cd.cd_operador as Operador, cd.cd_valor as Valor, cd.cd_conector as Conector FROM `condicoes` as cd INNER JOIN regras AS rg ON rg.rg_id=cd.cd_regra INNER JOIN contexto as ct on cd_contexto=ct.ct_id group BY cd.cd_id";
     
     
     
     
     
     this.resultSet = this.statement.executeQuery(query5);
     this.statement = (Statement) this.connection.createStatement();
     
     while(this.resultSet.next()){
      	
        
       contexto  = this.resultSet.getString("Contexto");
       valor     = this.resultSet.getString("Valor");
       operador  = this.resultSet.getString("Operador");
       conector     = this.resultSet.getString("Conector");
       query += "A."+contexto+" "+operador+" "+valor+" "+conector+" ";
    }
    
    
      
    }catch(SQLException e){
      	System.out.println("Errorrr aKI:" + e.getMessage());
      }
         
   	System.out.println(query);
    
    
     return query;   
      	
      }
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
    
    
   
           public void inserirFc(int fc){
       	
	        try{
	        	
	        String query = "INSERT INTO fc (`value`, `hr`) VALUES ('"+fc+"', now())" ;
	        this.statement.executeUpdate(query);
	        
	        }catch(Exception e){
	        		        	
	        	System.out.println("Errorrrr:" + e.getMessage());
	        	        	
	        }
	        	      	
	        }
           
           
           
           
           public void inserirAlert(int a){
       	
	        try{
	        	
	        String query = "INSERT INTO alerta (`value`, `hora`) VALUES ('"+a+"', now())" ;
	        this.statement.executeUpdate(query);
	        
	        }catch(Exception e){
	        		        	
	        	System.out.println("Errorrrr:" + e.getMessage());
	        	        	
	        }
	        	      	
	        }
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
	   public void inserirRota(Double lt, Double lg, String id, Double veloc, String imei){
       	
		   
		   
	        try{
	        	
	        String query = "INSERT INTO monitora (`uuid`, `imei`, `lat`, `lng`, `velocidade`, `data`) VALUES ('"+id+"', '"+imei+"', '"+lt+"', '"+lg+"', '"+veloc+"',now())" ;
	        this.statement.executeUpdate(query);
	        
	        }catch(Exception e){
	        	
	        	
	        	System.out.println("Errorrrr:" + e.getMessage());
	        	
	        	
	        	
	        }
	        	
	        	
	        	
	        }



	   public Double notificaVelocidade(String imei){
	       	
		   
		   
	       Double vel = null;
			try{
	        	
	        String query = "SELECT * FROM  `rotas` AS r INNER JOIN veiculos AS v ON r.id = v.id_rota WHERE v.imei ="+imei+";";
	        this.resultSet = this.statement.executeQuery(query);
	        this.statement = (Statement) this.connection.createStatement();
	        
	      while(this.resultSet.next()){
	        	
	         vel  = this.resultSet.getDouble("velocidade");
	    	  
	    	  //System.out.println(this.resultSet.getDouble("velocidade"));
	    	  
	    	  
	      }
	        
	        
	        }catch(SQLException e){
	        	
	        	
	        	System.out.println("Errorrr:" + e.getMessage());
	        	
	          	
	        }
			
			return vel;
			
	        	
	        	
	        	
	        }
	   
	   
	   
	   
	   
	
	   
	   
	   
	   
	   
	   
 public Double checaDistancia(String lt1, String lg1, String imei){
		   
		   Double lt = null;
		   Double lg = null;
		  
                   int rota = 0;
		    
     try{
		      	
		      String query3 = "SELECT * FROM  `monitora` AS m INNER JOIN veiculos AS v ON m.imei = v.imei WHERE v.imei ="+imei+";";
		      this.resultSet = this.statement.executeQuery(query3);
		      this.statement = (Statement) this.connection.createStatement();
		      
		    while(this.resultSet.next()){
		      	
		       rota  = this.resultSet.getInt("id_rota");
		  	  
		
		  	  
		  	  
		    }
		      
		      
		      }catch(SQLException e){
		      	
		      	
		      	System.out.println("Errorrr:" + e.getMessage());
		      	
		        	
		      }
		   
		   
		   
		   
		   
		   
		   
			try{
	        	
	        String query1 = "SELECT * FROM `monitora` as m inner join veiculos as v on m.imei = v.imei where v.imei != "+imei+" and id_rota = "+rota+" ;";
	        this.resultSet = this.statement.executeQuery(query1);
	        this.statement = (Statement) this.connection.createStatement();
	        
	        
	      while(this.resultSet.next()){
	        	
	         lt  = this.resultSet.getDouble("lat");
	         lg  = this.resultSet.getDouble("lng");
	    	 
	      
	    	  
	    	  
	      }
	        
	        
	        }catch(SQLException e){
	        	
	        	
	        	System.out.println("Errorrr:" + e.getMessage());
	        	
	          	
	        }
			
			
		
			 
			    double earthRadius = 6371;//kilometers
			    double dLat = Math.toRadians(lt - Double.parseDouble(lt1));
			    double dLng = Math.toRadians(lg - Double.parseDouble(lg1));
			    double sindLat = Math.sin(dLat / 2);
			    double sindLng = Math.sin(dLng / 2);
			    double a = Math.pow(sindLat, 2) + Math.pow(sindLng, 2)
			            * Math.cos(Math.toRadians(Double.parseDouble(lt1)))
			            * Math.cos(Math.toRadians(lt));
			    double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
			    double dist = earthRadius * c;
			
			
			
			
			
			
			
			return dist  * 1000; //em metros
		   
		   
		   
		   
		   
		   
		   
		   
	   }
	   
	   
	   
	   
 
 
 
 public Double distanciaDb(String imei){
    	
	   
	   
     Double ddb = null;
    
    
     try{
      	
      String query3 = "SELECT * FROM  `rotas` AS r INNER JOIN veiculos AS v ON r.id = v.id_rota WHERE v.imei ="+imei+";";
      this.resultSet = this.statement.executeQuery(query3);
      this.statement = (Statement) this.connection.createStatement();
      
    while(this.resultSet.next()){
      	
       ddb  = this.resultSet.getDouble("distancia");
  	  
  	  //System.out.println(this.resultSet.getDouble("velocidade"));
  	  
  	  
    }
      
      
      }catch(SQLException e){
      	
      	
      	System.out.println("Errorrr:" + e.getMessage());
      	
        	
      }
		
		return ddb;
		
      	
      	
      	
      }
 
 
 
 
 
 
 
 
 public String QueryGenarator(int id){
     
     String event, operator, value, conector, 
     query = "";
     int rowcount = 0;
    try{
      	
      String query5 = "SELECT * \n" +
                         "FROM event_rules_condition AS erc\n" +
                         "INNER JOIN context AS c ON erc.context_id = c.id\n" +
                      "WHERE erc.event_rules_id ="+id;
     
     this.resultSet = this.statement.executeQuery(query5);
     this.statement = (Statement) this.connection.createStatement();
     while(this.resultSet.next()){
      	
       event     = this.resultSet.getString("short_name");
       operator  = this.resultSet.getString("operator");
       value     = this.resultSet.getString("value");
       conector  = this.resultSet.getString("conector");
       query += ""+event+" "+operator+" "+value+" "+conector+" ";
       
    }
     rowcount = this.resultSet.last() ? this.resultSet.getRow() : 0; 
      
    }catch(SQLException e){
      	System.out.println("Errorrr:" + e.getMessage());
      }
           if(rowcount > 1){
             	return "where "+query;
	} else{
           return " ";
             }
      	
      }
 
 
 
 
 
 
 
 
 
 
  
  
  
  
  
    public String ContexGenarator(int id){
     
     String event, operator, conector, duration = " ";
   
       
    try{
        
        
        
        /*
        
        SELECT MIN( CAST( value AS DECIMAL ) ) AS v_min
FROM  `event_rules_condition` 
GROUP BY context_id
        
        
        
        
        SELECT MIN( CAST( erc.value AS DECIMAL ) ) AS v_min, er.duration FROM `event_rules_condition` as erc 
inner join event_rules as er ON erc.`event_rules_id` = er.id GROUP BY context_id
        
        
        
        SELECT * 
FROM  `event_rules_condition` AS erc
INNER JOIN event_rules AS er ON erc.`event_rules_id` = er.id
        
        
        
       
      	
      String query5 = "SELECT * \n" +
                        "FROM  `event_rules` \n" +
                      "WHERE  `id`="+id; */
        
        
        
        String query5 = "SELECT MIN( CAST( erc.value AS DECIMAL ) ) AS v_min, er.duration, c.short_name, erc.`operator` \n" +
"FROM  `event_rules_condition` AS erc\n" +
"INNER JOIN event_rules AS er ON erc.`event_rules_id` = er.id\n" +
"INNER JOIN context AS c ON erc.`context_id` = c.id\n" +
"GROUP BY context_id";
        
        
     
     this.resultSet = this.statement.executeQuery(query5);
     this.statement = (Statement) this.connection.createStatement();
     while(this.resultSet.next()){
      
       duration  = this.resultSet.getString("duration");
        
       
    }
    
    }catch(SQLException e){
      	System.out.println("Errorrr:" + e.getMessage());
      }
         	return duration;
 }
  
  
  
 
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
 
 
     public String ContextCreate(int id){
     
     String event, operator, conector, duration = " ";
   
       
    try{
      	
      String query5 = "SELECT * FROM `event_rules`  as er \n" +
"INNER JOIN event_rules_condition as erc ON er.id = erc.event_rules_id  \n" +
"WHERE `event_id`= 3";
     
     this.resultSet = this.statement.executeQuery(query5);
     this.statement = (Statement) this.connection.createStatement();
     while(this.resultSet.next()){
      
       duration  = this.resultSet.getString("duration");
        
       
    }
    
    }catch(SQLException e){
      	System.out.println("Errorrr:" + e.getMessage());
      }
         	return duration;
 }
 
 
 
 
 
	   
	   
       	
       	
       }
		
	
	


