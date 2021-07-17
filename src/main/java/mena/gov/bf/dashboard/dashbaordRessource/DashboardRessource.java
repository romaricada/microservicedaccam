package mena.gov.bf.dashboard.dashbaordRessource;

import mena.gov.bf.dashboard.dashboardService.DasboardService;
import mena.gov.bf.dashboard.entity.Dashboard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/api")
public class DashboardRessource {

@Autowired
    DasboardService dasboardService;
    /***
     *
     * @return
     */
    @GetMapping("/dashboard/dashboard-acceuil")
    public ResponseEntity<Dashboard> findAccueilInformation(@RequestParam Long idExercice) {
        return ResponseEntity.ok(dasboardService.getCurrentTask(idExercice));
    }


}
