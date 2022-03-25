package com.example.db.bean;

import com.example.db.entity.BuyRaw;
import com.example.db.entity.Employee;
import com.example.db.entity.Raw;
import com.example.db.exception.BudgetException;
import com.example.db.service.BuyRawService;
import com.example.db.service.EmployeeService;
import com.example.db.service.RawService;
import lombok.Getter;
import lombok.Setter;
import org.primefaces.PrimeFaces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.time.LocalDateTime;
import java.util.List;

@Component
@ManagedBean
@SessionScoped
@Getter
@Setter
public class BuyRawBean {
    private Long rawId;
    private Integer amount;
    private Float sum;
    private LocalDateTime date;
    private Long employeeId;
    private BuyRaw buyRaw;
    private List<BuyRaw> buyRaws;
    private List<Employee> employees;
    private List<Raw> raws;

    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private RawService rawService;
    @Autowired
    private BuyRawService buyRawService;

    public void init() {
        buyRaws = buyRawService.getAll();
        employees = employeeService.getAll();
        raws = rawService.getAll();
    }

    public void create() {
        BuyRaw buyRaw = new BuyRaw();
        buyRaw.setSum(sum);
        buyRaw.setRaw(rawService.getById(rawId));
        buyRaw.setEmployee(employeeService.getById(employeeId));
        buyRaw.setDate(date);
        buyRaw.setAmount(amount);
        try{
        buyRawService.save(buyRaw);
        }catch(BudgetException budgetException){
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("Exception",budgetException.getMessage()));
            PrimeFaces.current().ajax().update("messages");
        }finally {
            clean();
        }

    }

    public void delete(Long id) {
        buyRawService.deleteById(id);
    }

    public String navigateToUpdate(Long id) {
        buyRaw = buyRawService.getById(id);
        return "budget_update.xhtml?faces-redirect=true";
    }

    public void update() {
        buyRaw.setRaw(rawService.getById(rawId));
        buyRaw.setEmployee(employeeService.getById(employeeId));
        buyRawService.update(buyRaw);
        buyRaw.setSum(null);
        buyRaw.setRaw(null);
        buyRaw.setEmployee(null);
        buyRaw.setDate(null);
        buyRaw.setAmount(null);
    }

    private void clean() {
        sum = null;
        rawId = null;
        employeeId = null;
        date = null;
        amount = null;
    }
}
