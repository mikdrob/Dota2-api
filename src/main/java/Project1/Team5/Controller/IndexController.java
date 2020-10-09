package Project1.Team5.Controller;


import Project1.Team5.service.DataQuery;
import Project1.Team5.service.DotaResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequestMapping("/index")
@RestController
public class IndexController {

    @GetMapping
    public DotaResponse index(){
        DataQuery dataquery = new DataQuery();
        return dataquery.dataQuery();
    }


}
