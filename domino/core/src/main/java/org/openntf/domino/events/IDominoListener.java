/**
 *
 */
package org.openntf.domino.events;

import java.util.List;

/**
 * The OpenNTF Domino Listener interface, used to listen for certain events on an object that implements Base
 *
 * @author nfreeman
 *
 *
 */
public interface IDominoListener {
	/**
	 * Method that fires code for the events the listener support.
	 * <p>
	 * Example:<br/>
	 * The following code checks for and runs code for AFTER_CREATE_DOCUMENT and AFTER_UPDATE_DOCUMENT events
	 *
	 * <pre>
	 *
	 * public boolean eventHappened(IDominoEvent event) {
	 * 	try {
	 * 		if (event.getEvent().equals(Events.AFTER_CREATE_DOCUMENT)) {
	 * 			// Do something and return true if successful;
	 * 		}
	 * 		if (event.getEvent().equals(Events.AFTER_UPDATE_DOCUMENT)) {
	 * 			// Do something and return true if successful;
	 * 		}
	 *
	 * 		return false; // something went wrong, maybe an EnumEvent not implemented
	 * 	} catch (Exception e) {
	 * 		return false;
	 * 	}
	 * }
	 * </pre>
	 * </p>
	 *
	 * @param event
	 *            IDominoEvent holding a source, a target and a payload. Its getEvent() method returns the EnumEvent
	 * @return boolean success or failure of running the code
	 *
	 *
	 * @since openntf.domino 3.0.0
	 */
	public boolean eventHappened(IDominoEvent event);

	/**
	 * The events this listener provides code to act upon.
	 * <p>
	 * Example:<br/>
	 * The following code will define that AFTER_CREATE_DOCUMENT and AFTER_UPDATE_DOCUMENT events are supported by this Listener<br/>
	 *
	 * <pre>
	 * public List<EnumEvent> getEventTypes() {
	 * 	ArrayList<EnumEvent> eventList = new ArrayList<EnumEvent>();
	 * 	eventList.add(Events.AFTER_CREATE_DOCUMENT);
	 * 	eventList.add(Events.AFTER_UPDATE_DOCUMENT);
	 * 	return eventList;
	 * }
	 * </pre>
	 * </p>
	 *
	 * @return A List of EnumEvents the implementation supports. The current list of EnumEvents supported can be found in
	 *         {@link org.openntf.domino.ext.Database}
	 *
	 *
	 *         </code>
	 *
	 * @since openntf.domino 3.0.0
	 */
	public List<EnumEvent> getEventTypes();
}
