package com.example.db.bean;

import com.example.db.entity.Position;
import com.example.db.service.PositionService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.util.List;

@Component
@ManagedBean
@SessionScoped
@Getter
@Setter
public class PositionBean {
    private String positionName;
    private Position position;
    private List<Position> positions;

    @Autowired
    private PositionService positionService;

    private void init() {
        positions = positionService.getAll();
    }

    private void create() {
        Position position = new Position();
        position.setPosition(positionName);
    }

    private void delete(Long id) {
        positionService.deleteById(id);
    }

    private String navigateToUpdate(Long id) {
        position = positionService.getById(id);
        return "position_update.xhtml?faces-redirect=true";
    }

    private void update() {
        positionService.update(position);
        position.setPosition(null);
    }

    private void clean() {
        positionName = null;
    }
}
