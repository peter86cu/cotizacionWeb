package com.shopping.dashboard.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.shopping.dashboard.modelo.Wp_wsm_logUniqueVisit;


public interface VisitantesJPA extends JpaRepository<Wp_wsm_logUniqueVisit, Integer> {

	  @Query(value="SELECT COUNT(*),c.name,CASE MONTH(firstActionVisitTime)\r\n"
	  		+ "        WHEN 1 THEN 'Enero'\r\n"
	  		+ "        WHEN 2 THEN 'Febrero'\r\n"
	  		+ "        WHEN 3 THEN 'Marzo'\r\n"
	  		+ "        WHEN 4 THEN 'Abril'\r\n"
	  		+ "        WHEN 5 THEN 'Mayo'\r\n"
	  		+ "        WHEN 6 THEN 'Junio'\r\n"
	  		+ "        WHEN 7 THEN 'Julio'\r\n"
	  		+ "        WHEN 8 THEN 'Agosto'\r\n"
	  		+ "        WHEN 9 THEN 'Septiembre'\r\n"
	  		+ "        WHEN 10 THEN 'Octubre'\r\n"
	  		+ "        WHEN 11 THEN 'Noviembre'\r\n"
	  		+ "        WHEN 12 THEN 'Diciembre'\r\n"
	  		+ "    END AS mes, YEAR(firstActionVisitTime) AS anio FROM wp_wsm_logUniqueVisit v JOIN wp_wsm_countries c ON(c.id=v.countryId) GROUP BY  v.countryId,mes, anio", nativeQuery=true)
	    List<Object> visitantesMensualesPorPaises();
	  
	  
	  @Query(value="SELECT COUNT(*),CASE MONTH(firstActionVisitTime)\r\n"
		  		+ "        WHEN 1 THEN 'Enero'\r\n"
		  		+ "        WHEN 2 THEN 'Febrero'\r\n"
		  		+ "        WHEN 3 THEN 'Marzo'\r\n"
		  		+ "        WHEN 4 THEN 'Abril'\r\n"
		  		+ "        WHEN 5 THEN 'Mayo'\r\n"
		  		+ "        WHEN 6 THEN 'Junio'\r\n"
		  		+ "        WHEN 7 THEN 'Julio'\r\n"
		  		+ "        WHEN 8 THEN 'Agosto'\r\n"
		  		+ "        WHEN 9 THEN 'Septiembre'\r\n"
		  		+ "        WHEN 10 THEN 'Octubre'\r\n"
		  		+ "        WHEN 11 THEN 'Noviembre'\r\n"
		  		+ "        WHEN 12 THEN 'Diciembre'\r\n"
		  		+ "    END AS mes, YEAR(firstActionVisitTime) AS anio FROM wp_wsm_logUniqueVisit v JOIN wp_wsm_countries c ON(c.id=v.countryId) GROUP BY mes, anio ORDER BY mes DESC ", nativeQuery=true)
		    List<Object> visitantesMensuales();
	  
	
}
