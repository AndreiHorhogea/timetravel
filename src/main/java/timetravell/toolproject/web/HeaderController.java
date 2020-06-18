package timetravell.toolproject.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import timetravell.toolproject.repository.CategoryRepository;
import timetravell.toolproject.repository.ProjjectRepository;

@RestController
public class HeaderController {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProjjectRepository projjectRepository;
}
