import { apiCall, loggedUser, toastMessage } from "../utils/utils";
import { useState } from "react";
import { ENDPOINTS } from "../utils/statics";

function SendMessageButton({ event }) {
  const [subject, setSubject] = useState("");
  const [text, setText] = useState("");

  const sendMessageToAttendees = async (e) => {
    e.preventDefault();

    let message = {};
    message["subject"] = subject;
    message["text"] = text;
    await apiCall(ENDPOINTS.sendMessageToAttendees(event?.id), "POST", message);
    toastMessage("Message has been sent to attendees!");
  };

  return (
    <>
      <button
        type="button"
        className="btn btn-sm btn-warning"
        data-bs-toggle="modal"
        data-bs-target="#sendMessageModal"
      >
        Message Attendees <i className="bi bi-card-text"></i>
      </button>
      <div
        className="modal fade"
        id="sendMessageModal"
        tabIndex="-2"
        aria-labelledby="sendMessageModalLabel"
        aria-hidden="true"
      >
        <div className="modal-dialog modal-dialog-centered modal-lg">
          <div className="modal-content">
            <form onSubmit={sendMessageToAttendees}>
              <div className="modal-header">
                <h5 className="modal-title" id="sendMessageModalLabel">
                  Send message to all the attendees
                </h5>
                <button
                  type="button"
                  className="btn-close"
                  data-bs-dismiss="modal"
                  aria-label="Close"
                ></button>
              </div>
              <div className="modal-body">
                <div className="mb-3">
                  <label className="form-label">Subject</label>
                  <input
                    className="form-control"
                    id="subject"
                    rows="3"
                    value={subject}
                    onChange={(e) => setSubject(e.target.value)}
                  ></input>
                </div>
              </div>
              <div className="modal-body">
                <div className="mb-3">
                  <label className="form-label">Text</label>
                  <textarea
                    className="form-control"
                    id="text"
                    rows="3"
                    value={text}
                    onChange={(e) => setText(e.target.value)}
                  ></textarea>
                </div>
              </div>

              <div className="modal-footer">
                <button
                  type="button"
                  className="btn btn-sm btn-secondary"
                  data-bs-dismiss="modal"
                >
                  Close
                </button>
                <button
                  type="submit"
                  data-bs-dismiss="modal"
                  className="btn btn-sm btn-primary"
                >
                  Submit message
                </button>
              </div>
            </form>
          </div>
        </div>
      </div>
    </>
  );
}

export default SendMessageButton;
