/*
 * component:   "openEHR Reference Implementation"
 * description: "Class Message"
 * keywords:    "message"
 *
 * author:      "Rong Chen <rong@acode.se>"
 * support:     "Acode HB <support@acode.se>"
 * copyright:   "Copyright (c) 2004,2005 Acode HB, Sweden"
 * license:     "See notice at bottom of class"
 *
 * file:        "$URL: http://svn.openehr.org/ref_impl_java/BRANCHES/RM-1.0-update/libraries/src/java/org/openehr/rm/message/Message.java $"
 * revision:    "$LastChangedRevision: 2 $"
 * last_change: "$LastChangedDate: 2005-10-12 23:20:08 +0200 (Wed, 12 Oct 2005) $"
 */
package org.openehr.rm.message;

import org.openehr.rm.RMObject;
import org.openehr.rm.demographic.Party;
import org.openehr.rm.common.generic.Attestation;
import org.openehr.rm.support.identification.PartyReference;
import org.openehr.rm.datatypes.quantity.datetime.DvDateTime;
import org.openehr.rm.datatypes.quantity.DvOrdinal;
import org.apache.commons.lang.StringUtils;

import java.util.Set;

/**
 * The message envelope for an extract, indicating the sender and receiver
 * details, time and any other details required.
 *
 * @author Rong Chen
 * @version 1.0
 */
public class Message extends RMObject {

    /**
     * Enumeration of Initiator of message
     */
    public enum Initiator {
        SENDER, RECEIVER
    };

    /**
     * Constructs a Message
     *
     * @param timeSent
     * @param sender
     * @param receiver
     * @param senderNode
     * @param receiverNode
     * @param sendersReference
     * @param initiator
     * @param urgency
     * @param signature
     * @param parties
     * @param content
     * @throws IllegalArgumentException
     */
    public Message(DvDateTime timeSent, PartyReference sender,
                   PartyReference receiver, PartyReference senderNode,
                   PartyReference receiverNode, String sendersReference,
                   Initiator initiator, DvOrdinal urgency,
                   Attestation signature, Set<Party> parties,
                   MessageContent content) {
        if (timeSent == null) {
            throw new IllegalArgumentException("timeSent null");
        }
        if (sender == null) {
            throw new IllegalArgumentException("sender null");
        }
        if (receiver == null) {
            throw new IllegalArgumentException("receiver null");
        }
        if (StringUtils.isEmpty(sendersReference)) {
            throw new IllegalArgumentException(
                    "null or empty sendersReference");
        }
        if (senderNode == null) {
            throw new IllegalArgumentException("senderNode null");
        }
        if (receiverNode == null) {
            throw new IllegalArgumentException("receiverNode null");
        }
        if (initiator == null) {
            throw new IllegalArgumentException("initiator null");
        }

        if (urgency == null) {
            throw new IllegalArgumentException("urgency null");
        }
        if (parties != null && parties.isEmpty()) {
            throw new IllegalArgumentException("empty parties");
        }
        if (content == null) {
            throw new IllegalArgumentException("content null");
        }
        this.timeSent = timeSent;
        this.sender = sender;
        this.receiver = receiver;
        this.senderNode = senderNode;
        this.receiverNode = receiverNode;
        this.sendersReference = sendersReference;
        this.initiator = initiator;
        this.urgency = urgency;
        this.signature = signature;
        this.parties = parties;
        this.content = content;
    }

    /**
     * Date/time the message was sent.
     *
     * @return timeSent
     */
    public DvDateTime getTimeSent() {
        return timeSent;
    }

    /**
     * Party sending the extract.
     *
     * @return sender
     */
    public PartyReference getSender() {
        return sender;
    }

    /**
     * Party the extract is sent to.
     *
     * @return receiver
     */
    public PartyReference getReceiver() {
        return receiver;
    }

    /**
     * EHR node from which the message is sent.
     *
     * @return senderNode
     */
    public PartyReference getSenderNode() {
        return senderNode;
    }

    /**
     * EHR node receiving the message.
     *
     * @return receiverNode
     */
    public PartyReference getReceiverNode() {
        return receiverNode;
    }

    /**
     * Identification of message at sender s end.
     *
     * @return sendersReference
     */
    public String getSendersReference() {
        return sendersReference;
    }

    /**
     * Indicates which party - sender or receiver caused the message to be
     * created and sent. If the receiver,there was an EHR_REQUEST.
     * If the sender, there is no request, and the extract is being sent
     * unsolicited.
     *
     * @return initiator of this message
     */
    public Initiator getInitiator() {
        return initiator;
    }

    /**
     * Urgency with which receiver should deal with message
     *
     * @return urgency
     */
    public DvOrdinal getUrgency() {
        return urgency;
    }

    /**
     * Signature of message content.
     *
     * @return signature
     */
    public Attestation getSignature() {
        return signature;
    }

    /**
     * Parties referred to by all PARTY_REF and ATTESTATION instances in this
     * message instance.
     *
     * @return parties
     */
    public Set<Party> getParties() {
        return parties;
    }

    /**
     * The content of the message
     *
     * @return content
     */
    public MessageContent getContent() {
        return content;
    }

    /* fields */
    private DvDateTime timeSent;
    private PartyReference sender;
    private PartyReference receiver;
    private PartyReference senderNode;
    private PartyReference receiverNode;
    private String sendersReference;
    private Initiator initiator;
    private DvOrdinal urgency;
    private Attestation signature;
    private Set<Party> parties;
    private MessageContent content;
}

/*
 *  ***** BEGIN LICENSE BLOCK *****
 *  Version: MPL 1.1/GPL 2.0/LGPL 2.1
 *
 *  The contents of this file are subject to the Mozilla Public License Version
 *  1.1 (the 'License'); you may not use this file except in compliance with
 *  the License. You may obtain a copy of the License at
 *  http://www.mozilla.org/MPL/
 *
 *  Software distributed under the License is distributed on an 'AS IS' basis,
 *  WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License
 *  for the specific language governing rights and limitations under the
 *  License.
 *
 *  The Original Code is Message.java
 *
 *  The Initial Developer of the Original Code is Rong Chen.
 *  Portions created by the Initial Developer are Copyright (C) 2003-2004
 *  the Initial Developer. All Rights Reserved.
 *
 *  Contributor(s):
 *
 * Software distributed under the License is distributed on an 'AS IS' basis,
 * WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License
 * for the specific language governing rights and limitations under the
 * License.
 *
 *  ***** END LICENSE BLOCK *****
 */
