package my.vaadin.app;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.data.Binder;
import com.vaadin.data.Binder.Binding;
import com.vaadin.data.provider.DataProvider;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.shared.ui.ValueChangeMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Grid.Column;
import com.vaadin.ui.Grid.SelectionMode;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.components.grid.HeaderCell;
import com.vaadin.ui.components.grid.HeaderRow;
import com.vaadin.ui.themes.ValoTheme;

/**
 * This UI is the application entry point. A UI may either represent a browser window 
 * (or tab) or some part of a html page where a Vaadin application is embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is intended to be 
 * overridden to add component to the user interface and initialize non-component functionality.
 */
@Theme("mytheme")
public class MyUI extends UI {
    
	private TabSheet tabsheet = new TabSheet();
	
    private CustomerService service = CustomerService.getInstance();
    private Grid<Customer> grid = new Grid<>(Customer.class);
    private TextField filterText = new TextField();
    private CustomerForm form = new CustomerForm(this);
    
    private DataProvider<Customer, Void> dataProvider;

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        final VerticalLayout tab1 = new VerticalLayout();
        final VerticalLayout tab2 = new VerticalLayout();

        filterText.setPlaceholder("filter by name...");
        filterText.addValueChangeListener(e -> updateList());
        filterText.setValueChangeMode(ValueChangeMode.LAZY);

        Button clearFilterTextBtn = new Button(FontAwesome.TIMES);
        clearFilterTextBtn.setDescription("Clear the current filter");
        clearFilterTextBtn.addClickListener(e -> filterText.clear());

        CssLayout filtering = new CssLayout();
        filtering.addComponents(filterText, clearFilterTextBtn);
        filtering.setStyleName(ValoTheme.LAYOUT_COMPONENT_GROUP);

        Button addCustomerBtn = new Button("Add new customer");
        addCustomerBtn.addClickListener(e -> {
            grid.asSingleSelect().clear();
            form.setCustomer(new Customer());
        });

        HorizontalLayout toolbar = new HorizontalLayout(filtering, addCustomerBtn);
        
       
        //column grouping
        HeaderRow groupingHeader = grid.prependHeaderRow();
        groupingHeader.join(
            groupingHeader.getCell("firstName"),
            groupingHeader.getCell("lastName")).setText("Name");
        groupingHeader.join(
            groupingHeader.getCell("email")).setText("Details");
        
        //column width
        grid.getColumn("firstName").setWidth(150);
        
        grid.setColumns("firstName", "lastName", "email");
        
        
        //inline-ediing per column
        Binder<Customer> binder = grid.getEditor().getBinder();
        TextField nameField = new TextField();
        Binding<Customer, String> doneBinding = binder.bind(
        		nameField, Customer::getFirstName, Customer::setFirstName);
        grid.getColumn("firstName").setEditorBinding(doneBinding);
        grid.getEditor().setEnabled(true);
        
//        grid.setSelectionMode(SelectionMode.MULTI);
        
        

        HorizontalLayout main = new HorizontalLayout(grid, form);
        main.setSizeFull();
        grid.setSizeFull();
        main.setExpandRatio(grid, 1);
        
        //lazy loading
//        dataProvider = DataProvider.fromCallbacks(
//		// First callback fetches items based on a query
//		query -> {
//			// The index of the first item to load
//			int offset = query.getOffset();
//
//			// The number of items to load
//			int limit = query.getLimit();
//
//			List<Customer> persons = service.findAll(filterText.getValue(), offset, limit);
//
//			return persons.stream();
//		},
//		// Second callback fetches the number of items for a query
//		query -> (int) service.count());
//        grid.setDataProvider(dataProvider);
        

        tab1.addComponents(toolbar, main);
        tabsheet.addTab(tab1, "Tab 1");
        
        
        
        tab2.addComponents(new Grid<>(Customer.class), new Grid<>(Customer.class));
        tabsheet.addTab(tab2, "Tab 2");
        // fetch list of Customers from service and assign it to Grid
        updateList();

        setContent(tabsheet);

        form.setVisible(false);

        grid.asSingleSelect().addValueChangeListener(event -> {
            if (event.getValue() == null) {
                form.setVisible(false);
            } else {
                form.setCustomer(event.getValue());
            }
        });
    }

    public void updateList() {
        List<Customer> customers = service.findAll(filterText.getValue());
        grid.setItems(customers);
//        grid.setItems(new ArrayList<Customer>());
        
//        dataProvider.refreshAll();
//        grid.markAsDirtyRecursive();

    }

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}
