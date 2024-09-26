package org.example.view;

import org.example.controller.Admin;
import org.example.controller.Usuario;
import org.example.model.modelUsuario;
import org.example.util.CarregadorFonte;
import org.example.util.TratadorMouseHover;

import javax.sound.sampled.Clip;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class TelaLog extends JPanel
{
    private List<Usuario> ListaUs = modelUsuario.getUsuarios();
    private DefaultListModel<Usuario> lModel = new DefaultListModel<>();
    private JList<Usuario> listUsers = new JList<>(lModel);
    private final JScrollPane pnUS = new JScrollPane(listUsers);
    private final JLayeredPane Jlp = new JLayeredPane();
    private Usuario usSelected;

    private int indexAt;

    private final JButton selUsr = new JButton("Entrar");
    private final JButton adm = new JButton("Entrar como Admin");
    private final JButton del = new JButton("Excluir");
    private final JButton clear = new JButton("Excluir Todos");
    private final JButton edit = new JButton("Editar");
    private final JButton btnPswrdConf = new JButton("Confirmar");

    private final JTextField inpPsswrd = new JTextField();

    private final JPanel btnCont = new JPanel();
    private final JPanel pnSenha = new JPanel();
    private final JPanel pnSSubCont= new JPanel();

    private final JLabel txtInfo = new JLabel("Jogadores Cadastrados:");
    private final JLabel txtPswrText = new JLabel("Insira sua senha:");
    private final JLabel errLogin = new JLabel("Senha incorreta");

    private final Font fnt = CarregadorFonte.CarregaFonte("fonts/FT14.ttf",25f);
    private final Font fnt2= CarregadorFonte.CarregaFonte("fonts/space_invaders.ttf",25f);

    private final Color CorFundo = new Color(20, 31, 20);

    private void AttLista()
    {
        ListaUs = modelUsuario.getUsuarios();
        lModel.clear();

        for(Usuario us : ListaUs)
            lModel.addElement(us);
    }

    public TelaLog(Clip Click, Clip Hov)
    {
        AttLista();
        this.setPreferredSize(new Dimension(1000,800));
        this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        this.setBackground(Color.BLACK);

        Jlp.setSize(new Dimension(1000,700));

        pnSSubCont.setBackground(CorFundo);
        pnSSubCont.setLayout(new BoxLayout(pnSSubCont,BoxLayout.X_AXIS));
        pnSSubCont.setAlignmentY(Component.CENTER_ALIGNMENT);
        pnSSubCont.add(txtPswrText);
        pnSSubCont.add(inpPsswrd);
        pnSSubCont.add(btnPswrdConf);
        pnSSubCont.setVisible(true);

        errLogin.setAlignmentY(Component.BOTTOM_ALIGNMENT);

        //delimita o tamanho do painel
        pnSenha.setSize(new Dimension(900,250));
        //define os bounds para centralizá-lo na tela
        pnSenha.setBounds((Jlp.getSize().width - pnSenha.getWidth())/2,(Jlp.getSize().height - pnSenha.getHeight())/2,900,250);
        pnSenha.setBackground(CorFundo);
        pnSenha.setLayout(new BoxLayout(pnSenha,BoxLayout.Y_AXIS));;
        pnSenha.add(pnSSubCont);
        pnSenha.add(errLogin);
        pnSenha.setVisible(false);

        btnCont.setSize(new Dimension(1000,800));
        btnCont.setLayout(new BoxLayout(btnCont,BoxLayout.X_AXIS));
        btnCont.setBackground(Color.BLACK);
        btnCont.setBorder(new EmptyBorder(0,0,0,0));
        btnCont.add(selUsr);
        btnCont.add(edit);
        btnCont.add(del);
        btnCont.add(adm);
        btnCont.add(clear);
        btnCont.setVisible(true);

        selUsr.setBackground(Color.BLACK);
        selUsr.setSize(new Dimension(250, 200));
        selUsr.setForeground(Color.white);
        selUsr.setFont(fnt);
        selUsr.setVisible(true);

        edit.setBackground(Color.BLACK);
        edit.setSize(new Dimension(250, 200));
        edit.setForeground(Color.white);
        edit.setFont(fnt);
        edit.setVisible(true);

        del.setBackground(Color.BLACK);
        del.setSize(new Dimension(250, 200));
        del.setForeground(Color.white);
        del.setFont(fnt);
        del.setVisible(true);

        adm.setBackground(Color.BLACK);
        adm.setSize(new Dimension(250, 200));
        adm.setForeground(Color.white);
        adm.setFont(fnt);
        adm.setVisible(true);

        clear.setBackground(Color.BLACK);
        clear.setSize(new Dimension(250, 200));
        clear.setForeground(Color.white);
        clear.setFont(fnt);
        clear.setVisible(true);

        btnPswrdConf.setBackground(Color.BLACK);
        btnPswrdConf.setMaximumSize(new Dimension(250, 75));
        btnPswrdConf.setForeground(Color.white);
        btnPswrdConf.setFont(fnt);
        btnPswrdConf.setMargin(pnSenha.getInsets());
        btnPswrdConf.setVisible(true);

        inpPsswrd.setMaximumSize(new Dimension(800,75));
        inpPsswrd.addMouseListener(new TratadorMouseHover(Click,Hov,null,null,null));
        inpPsswrd.setMargin(pnSenha.getInsets());
        inpPsswrd.setBackground(CorFundo);
        inpPsswrd.setForeground(Color.white);
        inpPsswrd.setFont(fnt);

        txtInfo.setForeground(Color.white);
        txtInfo.setSize(new Dimension(200,200));
        txtInfo.setFont(fnt2);
        txtInfo.setVisible(true);
        txtInfo.setAlignmentX(Component.CENTER_ALIGNMENT);

        txtPswrText.setForeground(Color.white);
        txtPswrText.setSize(new Dimension(75,75));
        txtPswrText.setFont(fnt2);
        txtPswrText.setVisible(true);
        txtPswrText.setAlignmentX(Component.CENTER_ALIGNMENT);

        errLogin.setForeground(Color.white);
        errLogin.setPreferredSize(new Dimension(150,50));
        errLogin.setFont(fnt2);
        errLogin.setVisible(false);
        errLogin.setAlignmentX(Component.CENTER_ALIGNMENT);

        listUsers.setBackground(Color.BLACK);
        listUsers.setForeground(Color.white);
        listUsers.setFont(fnt);
        listUsers.setVisible(true);

        listUsers.addMouseListener(new MouseListener() {
            //listUsers.locationToIndex(e.getPoint()); pega o valor do item de click
            //na lista
            @Override
            public void mouseClicked(MouseEvent e) {
                if(Click != null)
                {
                    Click.setFramePosition(0);
                    Click.start();
                }

                if(e.getButton() == 1)
                {
                    int index = listUsers.locationToIndex(e.getPoint());
                    if(index >=0)
                    {
                        usSelected = listUsers.getModel().getElementAt(index);
                        System.out.println("index:"+index);
                        System.out.println("id:"+usSelected.getId());
                        System.out.println(usSelected instanceof Admin);
                        indexAt = index;
                    }
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                if(Hov != null)
                {
                    Hov.setFramePosition(0);
                    Hov.start();
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if(Hov != null)
                    Hov.stop();
            }
        });
        listUsers.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                pnSenha.setVisible(false);
            }
        });
        pnSenha.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {

            }

            @Override
            public void focusLost(FocusEvent e) {
                pnSenha.setVisible(false);
            }
        });

        btnPswrdConf.addMouseListener(new TratadorMouseHover(Click,Hov,null,null,null));
        del.addMouseListener(new TratadorMouseHover(Click,Hov,null,null,null));
        edit.addMouseListener(new TratadorMouseHover(Click,Hov,null,null,null));
        adm.addMouseListener(new TratadorMouseHover(Click,Hov,null,null,null));
        selUsr.addMouseListener(new TratadorMouseHover(Click,Hov,null,null,null));
        clear.addMouseListener(new TratadorMouseHover(Click,Hov,null,null,null));


        clear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                modelUsuario.deletarTodos();
                AttLista();
                revalidate();
                repaint();

                modelUsuario.numUsers = 0;
            }
        });

        selUsr.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                pnSenha.setVisible(true);
            }
        });
        btnPswrdConf.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                usSelected.setSenha(inpPsswrd.getText());
                if(usSelected.login())
                {
                    Fase1.initFase1();
                    FramePrincipal.CarregarPag("Fase1");
                }
                else
                    errLogin.setVisible(true);

            }
        });
        del.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean deltd = modelUsuario.deletar(usSelected);
                if(deltd)
                {
                    AttLista();
                    revalidate();
                    repaint();
                }
                else
                    System.out.println("Falha ao deletar usuário");
            }
        });
        pnUS.setSize(new Dimension(1000,700));
        pnUS.setMinimumSize(new Dimension(900,700));
        pnUS.setBackground(Color.BLACK);
        pnUS.setVisible(true);

        Jlp.add(pnUS,JLayeredPane.DEFAULT_LAYER);
        Jlp.add(pnSenha,JLayeredPane.PALETTE_LAYER);
        Jlp.setVisible(true);

        this.add(btnCont);
        this.add(txtInfo);
        this.add(Jlp);
        this.setVisible(true);


    }
}
