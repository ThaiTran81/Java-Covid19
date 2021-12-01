package com.cv19.swing;

import com.cv19.view.event.EventMenuCallBack;
import com.cv19.view.event.EventMenuSelected;
import com.cv19.view.model.Model_Menu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.ListModel;
import javax.swing.SwingUtilities;

public class ListMenu<E extends Object> extends JList<E> {

    private final DefaultListModel model;
    private EventMenuSelected event = null;
    private int selectedIndex = -1;

    public ListMenu() {
        model = new DefaultListModel();
        super.setModel(model);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent me) {
                if (SwingUtilities.isLeftMouseButton(me)) {
                    int index = locationToIndex(me.getPoint());
                    runEvent(index);
                }
            }
        });
    }
    private void runEvent(int indexChange) {
            event.menuSelected(indexChange);
    }

    @Override
    public ListCellRenderer<? super E> getCellRenderer() {
        return new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                Model_Menu data;
                if (value instanceof Model_Menu) {
                    data = (Model_Menu) value;
                } else {
                    data = new Model_Menu("1", value + "", Model_Menu.MenuType.MENU);
                }
                MenuItem item = new MenuItem(data);
//                item.setSelected(index == selectedIndex);
                
                item.setSelected(isSelected || index == selectedIndex);
                return item;
            }
        };
    }

    @Override
    public void setModel(ListModel<E> lm) {
        for (int i = 0; i < lm.getSize(); i++) {
            model.addElement(lm.getElementAt(i));
        }
    }

    public void addItem(Model_Menu data) {
        model.addElement(data);
    }

    public void selectedIndex(int index) {
        this.selectedIndex = index;
    }

    public void addEventSelectedMenu(EventMenuSelected e) {
       event = e;
    }
}
