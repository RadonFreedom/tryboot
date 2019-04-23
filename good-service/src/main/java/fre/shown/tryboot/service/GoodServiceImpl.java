package fre.shown.tryboot.service;

import fre.shown.tryboot.dao.GoodDAO;
import org.springframework.stereotype.Service;

/**
 * @author Radon Freedom
 * created at 2019.04.22 下午8:18
 */

@Service
public class GoodServiceImpl implements GoodService {

    private final GoodDAO goodDAO;

    public GoodServiceImpl(GoodDAO goodDAO) {
        this.goodDAO = goodDAO;
    }
}
