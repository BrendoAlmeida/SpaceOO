package org.example.view;

import org.example.controller.Personagem;
import org.example.model.modelPersonagem;
import org.example.util.*;
import org.example.controller.Usuario;
import org.example.model.modelUsuario;

import javax.sound.sampled.Clip;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
/*
Igor Correa Trifilio Campos 202365092A
Brendo Lee Visconde de Almeida	202365067A
Gabriel Toledo Gonçalves Barreto 202365083A
*/
public class TelaLog extends JPanel
{
    private static List<Usuario> ListaUs = modelUsuario.getUsuarios();
    private static DefaultListModel<Usuario> lModel = new DefaultListModel<>();
    private static JList<Usuario> listUsers = new JList<>(lModel);

    private final JScrollPane pnUS = new JScrollPane(listUsers);
    private final JLayeredPane Jlp = new JLayeredPane();
    private Usuario usSelected;
    private boolean logAdm;
    private boolean delAcc;


    private final JButton selUsr = new JButton("Entrar");
    private final JButton adm = new JButton("Entrar como Admin");
    private final JButton del = new JButton("Excluir");
    private final JButton clear = new JButton("Excluir Todos");
    private final JButton edit = new JButton("Editar");
    private final JButton btnPswrdConf = new JButton("Confirmar");
    private final JButton btnPswrdEdit = new JButton("Confirmar");
    private final JButton confEdit = new JButton("Confirmar");

    private final JTextField inpPsswrd = new JTextField();
    private final JTextField inpPsswrdLogAdm = new JTextField();

    private final JTextField setVida = new JTextField("Definir vida");
    private final JTextField setVel = new JTextField("Definir velocidade");
    private final JTextField setVelAt = new JTextField("Definir vel de ataque");

    private final JPanel btnCont = new JPanel();
    private final JPanel pnPopFlex = new JPanel();
    private final JPanel pnSSubCont= new JPanel();
    private final JPanel pnContLogAdm = new JPanel();
    private final JPanel pnEdit = new JPanel();


    private final JLabel txtInfo = new JLabel("Jogadores Cadastrados:");
    private final JLabel txtPswrText = new JLabel("Insira sua senha:");
    private final JLabel txtPswrTextLogAdm = new JLabel("Insira a senha do usuario:");
    private final JLabel errLogin = new JLabel();

    private final Font fnt = CarregadorFonte.CarregaFonte("fonts/FT14.ttf",25f);
    private final Font fnt2= CarregadorFonte.CarregaFonte("fonts/space_invaders.ttf",25f);

    private final Color CorFundo = new Color(20, 31, 20);

    public static void AttLista()
    {
        ListaUs = modelUsuario.getUsuarios();
        lModel.clear();

        for(Usuario us : ListaUs)
        {
            if(!ListaUs.isEmpty())
                listUsers.setCellRenderer(new ListRend());

            lModel.addElement(us);
        }

    }
    public TelaLog(Clip Click, Clip Hov) {
        AttLista();
        ConfigurarThis();
        ConfigurarJpanels();
        ConfigurarJButtons();
        ConfigurarJList();
        ConfigurarJLabels();
        ConfigurarListenersAc(Click, Hov);
        ConfigurarListenersHov(Click,Hov);

        this.add(btnCont);
        this.add(txtInfo);
        this.add(Jlp);
        this.setVisible(true);
    }
    private void ConfigurarJpanels()
    {
        Jlp.setSize(new Dimension(1000, 700));

        pnContLogAdm.setBackground(CorFundo);
        pnContLogAdm.setLayout(new BoxLayout(pnContLogAdm, BoxLayout.X_AXIS));
        pnContLogAdm.setAlignmentY(Component.CENTER_ALIGNMENT);
        pnContLogAdm.add(txtPswrTextLogAdm);
        pnContLogAdm.add(inpPsswrdLogAdm);
        pnContLogAdm.setVisible(true);

        pnSSubCont.setBackground(CorFundo);
        pnSSubCont.setLayout(new BoxLayout(pnSSubCont, BoxLayout.X_AXIS));
        pnSSubCont.setAlignmentY(Component.CENTER_ALIGNMENT);
        pnSSubCont.add(txtPswrText);
        pnSSubCont.add(inpPsswrd);
        pnSSubCont.add(btnPswrdConf);
        pnSSubCont.setVisible(true);

        errLogin.setAlignmentY(Component.BOTTOM_ALIGNMENT);

        //delimita o tamanho do painel
        pnPopFlex.setSize(new Dimension(900, 250));
        //define os bounds para centralizá-lo na tela
        pnPopFlex.setBounds((Jlp.getSize().width - pnPopFlex.getWidth()) / 2, (Jlp.getSize().height - pnPopFlex.getHeight()) / 2, 900, 250);
        pnPopFlex.setBackground(CorFundo);
        pnPopFlex.setLayout(new BoxLayout(pnPopFlex, BoxLayout.Y_AXIS));
        pnPopFlex.add(pnSSubCont);
        pnPopFlex.add(errLogin);
        pnPopFlex.setVisible(false);

        pnUS.setSize(new Dimension(1000, 700));
        pnUS.setMinimumSize(new Dimension(900, 700));
        pnUS.setBackground(Color.BLACK);
        pnUS.setVisible(true);

        Jlp.add(pnUS, JLayeredPane.DEFAULT_LAYER);
        Jlp.add(pnPopFlex, JLayeredPane.PALETTE_LAYER);
        Jlp.setVisible(true);

        btnCont.setSize(new Dimension(1000, 800));
        btnCont.setLayout(new BoxLayout(btnCont, BoxLayout.X_AXIS));
        btnCont.setBackground(Color.BLACK);
        btnCont.setBorder(new EmptyBorder(0, 0, 0, 0));
        btnCont.add(selUsr);
        btnCont.add(edit);
        btnCont.add(del);
        btnCont.add(adm);
        btnCont.add(clear);
        btnCont.setVisible(true);

        pnEdit.setPreferredSize(new Dimension(600,600));
        pnEdit.setLayout(new BoxLayout(pnEdit,BoxLayout.Y_AXIS));
        pnEdit.setBackground(CorFundo);

        pnEdit.add(setVida);
        pnEdit.add(setVel);
        pnEdit.add(setVelAt);
        pnEdit.add(confEdit);
        pnEdit.setVisible(true);
    }
    private void ConfigurarJLabels()
    {
        txtInfo.setForeground(Color.white);
        txtInfo.setSize(new Dimension(200, 200));
        txtInfo.setFont(fnt2);
        txtInfo.setVisible(true);
        txtInfo.setAlignmentX(Component.CENTER_ALIGNMENT);

        txtPswrText.setForeground(Color.white);
        txtPswrText.setSize(new Dimension(75, 75));
        txtPswrText.setFont(fnt2);
        txtPswrText.setVisible(true);
        txtPswrText.setAlignmentX(Component.CENTER_ALIGNMENT);

        txtPswrTextLogAdm.setForeground(Color.white);
        txtPswrTextLogAdm.setSize(new Dimension(75, 75));
        txtPswrTextLogAdm.setFont(fnt2);
        txtPswrTextLogAdm.setVisible(true);
        txtPswrTextLogAdm.setAlignmentX(Component.CENTER_ALIGNMENT);

        errLogin.setForeground(Color.white);
        errLogin.setPreferredSize(new Dimension(150, 50));
        errLogin.setFont(fnt);
        errLogin.setVisible(false);
        errLogin.setAlignmentX(Component.CENTER_ALIGNMENT);
    }
    private void ConfigurarJButtons()
    {
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
        btnPswrdConf.setMargin(pnPopFlex.getInsets());
        btnPswrdConf.setVisible(true);

        btnPswrdEdit.setBackground(Color.BLACK);
        btnPswrdEdit.setMaximumSize(new Dimension(250, 75));
        btnPswrdEdit.setForeground(Color.white);
        btnPswrdEdit.setFont(fnt);
        btnPswrdEdit.setMargin(pnPopFlex.getInsets());
        btnPswrdEdit.setVisible(true);

        inpPsswrd.setMaximumSize(new Dimension(800, 75));
        inpPsswrd.setMargin(pnPopFlex.getInsets());
        inpPsswrd.setBackground(CorFundo);
        inpPsswrd.setForeground(Color.white);
        inpPsswrd.setFont(fnt);

        inpPsswrdLogAdm.setMaximumSize(new Dimension(500, 75));
        inpPsswrdLogAdm.setMargin(pnPopFlex.getInsets());
        inpPsswrdLogAdm.setBackground(CorFundo);
        inpPsswrdLogAdm.setForeground(Color.white);
        inpPsswrdLogAdm.setFont(fnt);

        setVida.setMaximumSize(new Dimension(600, 75));
        setVida.setMargin(pnPopFlex.getInsets());
        setVida.setBackground(CorFundo);
        setVida.setForeground(Color.white);
        setVida.setFont(fnt);

        setVel.setMaximumSize(new Dimension(600, 75));
        setVel.setMargin(pnPopFlex.getInsets());
        setVel.setBackground(CorFundo);
        setVel.setForeground(Color.white);
        setVel.setFont(fnt);

        setVelAt.setMaximumSize(new Dimension(600, 75));
        setVelAt.setMargin(pnPopFlex.getInsets());
        setVelAt.setBackground(CorFundo);
        setVelAt.setForeground(Color.white);
        setVelAt.setFont(fnt);

        confEdit.setBackground(Color.BLACK);
        confEdit.setMaximumSize(new Dimension(250, 75));
        confEdit.setForeground(Color.white);
        confEdit.setFont(fnt);
        confEdit.setMargin(pnPopFlex.getInsets());
        confEdit.setVisible(true);

    }
    private void ConfigurarListenersAc(Clip Click, Clip Hov)
    {
        listUsers.addMouseListener(new MouseListener() {
            //listUsers.locationToIndex(e.getPoint()); pega o valor do item de click
            //na lista
            @Override
            public void mouseClicked(MouseEvent e) {
                if (Click != null) {
                    Click.setFramePosition(0);
                    Click.start();
                }

                if (e.getButton() == 1) {
                    int index = listUsers.locationToIndex(e.getPoint());
                    if (index >= 0) {
                        usSelected = listUsers.getModel().getElementAt(index);
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
                if (Hov != null) {
                    Hov.setFramePosition(0);
                    Hov.start();
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (Hov != null)
                    Hov.stop();
            }
        });
        listUsers.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                pnPopFlex.setVisible(false);
            }
        });
        pnPopFlex.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {

            }

            @Override
            public void focusLost(FocusEvent e) {
                //pnSenha.setVisible(false);
            }
        });
        selUsr.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pnPopFlex.setVisible(true);
                btnPswrdConf.setVisible(true);
                inpPsswrd.setVisible(true);
            }
        });
        btnPswrdConf.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AcBtnVef(logAdm,delAcc);
            }
        });
        del.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                pnPopFlex.removeAll();
                pnPopFlex.add(pnSSubCont);
                pnPopFlex.add(errLogin);

                txtPswrText.setVisible(true);
                inpPsswrd.setVisible(true);
                btnPswrdConf.setVisible(true);
                errLogin.setText("");

                pnPopFlex.setVisible(true);
                txtPswrText.setText("Insira a senha do jogador selecionado:");

                delAcc = true;
                logAdm = false;
            }
        });
        clear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(modelUsuario.getIsAdm(usSelected))
                {
                    modelUsuario.deletarTodos();
                    AttLista();
                    revalidate();
                    repaint();

                    delAcc = false;
                    logAdm = false;
                }
                else
                {
                    pnPopFlex.setVisible(true);
                    pnPopFlex.removeAll();

                    errLogin.setText("Usuario selecionado da lista precisa ser administrador");
                    pnPopFlex.add(errLogin);
                    errLogin.setVisible(true);

                }
            }
        });
        adm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pnPopFlex.removeAll();
                pnPopFlex.add(pnContLogAdm);
                pnPopFlex.add(pnSSubCont);
                pnPopFlex.add(errLogin);
                pnPopFlex.setVisible(true);

                logAdm = true;
                delAcc = false;

                txtPswrText.setText("Insira a senha de administrador:");
                errLogin.setText("");
                errLogin.setVisible(true);
            }
        });
        edit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(modelUsuario.getIsAdm(usSelected))
                {
                    pnSSubCont.remove(btnPswrdConf);
                    pnSSubCont.add(btnPswrdEdit);
                    pnPopFlex.add(pnSSubCont);
                    pnPopFlex.add(errLogin);
                    errLogin.setText("");
                    pnPopFlex.setVisible(true);
                }
                else
                {
                    pnPopFlex.setVisible(true);
                    pnPopFlex.removeAll();

                    errLogin.setText("Usuario selecionado da lista precisa ser administrador");
                    pnPopFlex.add(errLogin);
                    errLogin.setVisible(true);
                }
            }
        });

        btnPswrdEdit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                usSelected.setSenhaLocal(inpPsswrd.getText());
                if(usSelected.login())
                {
                    pnPopFlex.removeAll();
                    pnPopFlex.add(pnEdit);
                    pnPopFlex.setVisible(true);

                    revalidate();
                    repaint();
                }
            }
        });

        setVida.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                setVida.setText("");
            }

            @Override
            public void focusLost(FocusEvent e) {

            }
        });
        setVel.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                setVel.setText("");
            }

            @Override
            public void focusLost(FocusEvent e) {

            }
        });
        setVelAt.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                setVelAt.setText("");
            }

            @Override
            public void focusLost(FocusEvent e) {

            }
        });

        confEdit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                Personagem p = modelPersonagem.getPersonagem(1);
                EditarPersonagem(p);

                PersonagemSel.setOBPsel(p);
                FramePrincipal.IniciaFase(1, p,0);
            }
        });
    }
    private void ConfigurarListenersHov(Clip Click, Clip Hov)
    {
        btnPswrdConf.addMouseListener(new TratadorMouseHover(Click, Hov));
        del.addMouseListener(new TratadorMouseHover(Click, Hov));
        edit.addMouseListener(new TratadorMouseHover(Click, Hov));
        adm.addMouseListener(new TratadorMouseHover(Click, Hov));
        selUsr.addMouseListener(new TratadorMouseHover(Click, Hov));
        clear.addMouseListener(new TratadorMouseHover(Click, Hov));
        inpPsswrd.addMouseListener(new TratadorMouseHover(Click, Hov));
        confEdit.addMouseListener(new TratadorMouseHover(Click,Hov));
    }

    private void ConfigurarThis()
    {
        this.setPreferredSize(new Dimension(1000, 800));
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBackground(Color.BLACK);
    }

    private void ConfigurarJList()
    {
        listUsers.setBackground(Color.BLACK);
        listUsers.setForeground(Color.white);
        listUsers.setFont(fnt);
        listUsers.setVisible(true);
    }

    private void AcBtnVef(boolean logAdm, boolean delAcc)
    {
        if(!logAdm)
        {
            usSelected.setSenhaLocal(inpPsswrd.getText());
            if(usSelected!=null)
            {
                if(usSelected.login())
                {
                    if(delAcc)
                    {//Se ele quer deletar a conta selecionada
                        boolean deltd = modelUsuario.deletarPorNome(usSelected);
                        if (deltd)
                        {
                            AttLista();
                            revalidate();
                            repaint();
                        } else
                            System.out.println("Falha ao deletar usuário");

                        delAcc = false;

                        pnPopFlex.setVisible(false);
                        inpPsswrd.setText("");
                        inpPsswrdLogAdm.setText("");
                    }
                    else
                    {
                        UsuarioJogando.setUserJog(usSelected);
                        FramePrincipal.CarregarPag("SelPerso");
                    }
                }//se o login deu erro
                else
                {
                    errLogin.setText("Senha incorreta");
                    errLogin.setVisible(true);
                }
            }
            else
            {
                errLogin.setText("Selecione um usuário na lista");
                errLogin.setVisible(true);
            }
        }
        else
        {
            if(inpPsswrd.getText().equals(Admin.getSenha()))
            {
                usSelected.setSenhaLocal(inpPsswrdLogAdm.getText());

                if(usSelected.login())
                {
                    usSelected.setIsAdmin();
                    AttLista();

                    pnPopFlex.setVisible(true);
                    inpPsswrd.setText("");
                    inpPsswrdLogAdm.setText("");

                    pnPopFlex.removeAll();
                    pnPopFlex.add(errLogin);

                    errLogin.setText("Usuário selecionado da lista agora é admin");
                    errLogin.setVisible(true);

                    btnPswrdConf.setVisible(false);
                    inpPsswrd.setVisible(false);
                }
                else
                {
                    errLogin.setText("Senha do usuário incorreta");
                }
                revalidate();
                repaint();
            }
            else
            {
                errLogin.setText(errLogin.getText()+"Senha do admin incorreta");
                errLogin.setVisible(true);
            }
        }
    }

    private void EditarPersonagem(Personagem perso)
    {
        perso.setVida(Integer.parseInt(setVida.getText()));
        perso.setVelocidade(Integer.parseInt(setVel.getText()));
        perso.setDelayAtirar(Integer.parseInt(setVelAt.getText()));
    }
}
