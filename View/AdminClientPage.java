package View;

import Models.Client;
import Models.Hotel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.*;
import java.awt.*;
import java.util.Vector;

public class AdminClientPage extends JFrame {
    private JTable clientTable;

    public AdminClientPage(Hotel hotel) {
        setTitle("Admin – Client Management");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 600);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        getContentPane().setBackground(Color.WHITE);

        // --- NORTH: custom Header (you already have this) ---
        Header header = new Header();
        add(header, BorderLayout.NORTH);

        // --- WEST: your existing LeftPanel ---
        LeftPanel leftPanel = new LeftPanel();
        add(leftPanel, BorderLayout.WEST);

        // --- CENTER: table area ---
        String[] columnNames = { "ID", "Name", "Phone", "Email", "Banned" };
        Object[][] data = prepareClientTableData(hotel.getListClient());
        DefaultTableModel model = new DefaultTableModel(data, columnNames) {
            // make all cells non-editable
            @Override public boolean isCellEditable(int row, int col) {
                return false;
            }
        };
        clientTable = new JTable(model);
        clientTable.setRowHeight(40);
        clientTable.setIntercellSpacing(new Dimension(0, 0));
        clientTable.setShowGrid(false);
        clientTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        clientTable.setFillsViewportHeight(true);

        // — HEADER STYLING —
        JTableHeader th = clientTable.getTableHeader();
        th.setPreferredSize(new Dimension(th.getWidth(), 50));
        th.setDefaultRenderer(new HeaderRenderer());

        // — ALTERNATE ROW COLORS & PADDING —
        clientTable.setDefaultRenderer(Object.class, new ZebraStripeRenderer());

        // — “Banned” BADGE RENDERER —
        clientTable.getColumnModel()
                   .getColumn(4)
                   .setCellRenderer(new BadgeRenderer());
        
        JScrollPane scroll = new JScrollPane(clientTable);
        scroll.setBorder(BorderFactory.createEmptyBorder(10, 20, 20, 20));
        add(scroll, BorderLayout.CENTER);

        setVisible(true);
    }

    private Object[][] prepareClientTableData(Vector<Client> clients) {
        Object[][] data = new Object[clients.size()][5];
        for (int i = 0; i < clients.size(); i++) {
            Client c = clients.get(i);
            data[i][0] = c.getIdClient();
            data[i][1] = c.getNom() + " " + c.getPrenom();
            data[i][2] = c.getTelephone();
            data[i][3] = c.getEmail();
            data[i][4] = c.isBanned() ? "Yes" : "No";
        }
        return data;
    }

    // Renderer for the table header
    private static class HeaderRenderer extends DefaultTableCellRenderer {
        public HeaderRenderer() {
            setOpaque(true);
            setBackground(Color.WHITE);
            setHorizontalAlignment(CENTER);
            setFont(getFont().deriveFont(Font.BOLD, 16f));
            setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(220,220,220)));
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value,
                                                       boolean isSelected, boolean hasFocus, int row, int col) {
            super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, col);
            setText(value.toString());
            return this;
        }
    }

    // Renderer that alternates row colors and adds padding
    private static class ZebraStripeRenderer extends DefaultTableCellRenderer {
        private static final Color EVEN = Color.WHITE;
        private static final Color ODD  = new Color(245, 245, 245);
        private static final EmptyBorder PADDING = new EmptyBorder(0, 10, 0, 10);

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value,
                                                       boolean isSelected, boolean hasFocus, int row, int col) {
            super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, col);
            setBorder(PADDING);
            if (isSelected) {
                setBackground(new Color(200, 230, 255));
            } else {
                setBackground(row % 2 == 0 ? EVEN : ODD);
            }
            setForeground(Color.DARK_GRAY);
            return this;
        }
    }

    // Renderer for the “Banned” column to display colored badges
    private static class BadgeRenderer extends DefaultTableCellRenderer {
        private static final Color RED_BG   = new Color(240, 80, 80);
        private static final Color GREEN_BG = new Color(80, 200, 120);
        private static final EmptyBorder BADGE_PADDING = new EmptyBorder(5, 12, 5, 12);

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value,
                                                       boolean isSelected, boolean hasFocus, int row, int col) {
            JLabel lbl = new JLabel(value.toString());
            lbl.setOpaque(true);
            lbl.setBorder(BADGE_PADDING);
            lbl.setFont(lbl.getFont().deriveFont(Font.BOLD, 12f));
            lbl.setHorizontalAlignment(CENTER);
            if ("Yes".equals(value)) {
                lbl.setBackground(RED_BG);
                lbl.setForeground(Color.WHITE);
            } else {
                lbl.setBackground(GREEN_BG);
                lbl.setForeground(Color.WHITE);
            }
            // override zebra selection
            if (isSelected) {
                lbl.setBackground(lbl.getBackground().darker());
            }
            return lbl;
        }
    }
}
