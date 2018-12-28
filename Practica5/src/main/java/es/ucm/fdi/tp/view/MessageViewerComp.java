package es.ucm.fdi.tp.view;

import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import es.ucm.fdi.tp.base.model.GameAction;
import es.ucm.fdi.tp.base.model.GameState;

public class MessageViewerComp<S extends GameState<S, A>, A extends GameAction<S, A>>
		extends MessageViewer<S, A> {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1720984227293928395L;

	private JTextArea msgArea;

	public MessageViewerComp() {
		initGUI();
	}

	private void initGUI() {
		this.setBorder(BorderFactory.createTitledBorder("Status Messages"));
		msgArea = new JTextArea();
		msgArea.setEditable(false);
		msgArea.setLineWrap(true);
		msgArea.setWrapStyleWord(true);
		JScrollPane sclMsgArea = new JScrollPane(msgArea);
		sclMsgArea
				.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		sclMsgArea.setPreferredSize(new Dimension(150, 200));
		this.add(sclMsgArea);
		this.setOpaque(true);
		this.setVisible(true);
	}

	@Override
	public void addContent(String msg) {
		this.msgArea.append("* " + msg + "\n");
	}

	@Override
	public void setContent(String msg) {
		this.msgArea.setText("* " + msg + "\n");
	}

	@Override
	public void enable() {
	}

	@Override
	public void disable() {
	}

	@Override
	public void update(S state) {
	}

	@Override
	public void setPlayersInfoViewer(PlayersInfoViewer<S, A> playersInfoViewer) {
	}

	@Override
	public void setGameController(GameController<S, A> gameCtrl) {
	}

}
