package com.ogawalucas.class04practice04activity.jsf;

import com.ogawalucas.class04practice04activity.ejb.ScoreboardEjb;
import com.ogawalucas.class04practice04activity.model.ScoreboardItem;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

@Named(value = "scoreboardJsf")
@RequestScoped
public class ScoreboardJsf {

    @EJB
    private ScoreboardEjb ejb;

    
    public ScoreboardJsf() {
        
    }
    
    public List<ScoreboardItem> getAll() {
        return ejb.getAll();
    }
}
