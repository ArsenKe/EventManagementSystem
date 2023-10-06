import { useEffect, useState } from "react";
import Empty from "../components/Empty";
import { apiCall, loggedUser, toastMessage } from "../utils/utils";
import { ENDPOINTS } from "../utils/statics";
import { Link, useParams } from "react-router-dom";
import TimeAgo from "../components/TimeAgo";
import SelectSearchComponent from "../components/SelectSearchComponent";
import { isMarkedEvent } from "./helpers";
import { useNavigate } from "react-router-dom";
import SendMessageButton from "../components/SendMessageButton";

function EventsDetailsPage() {
  const history = useNavigate();
  const { id } = useParams();
  const [event, setEvent] = useState();
  const [tagList, setTagList] = useState();
  const [selectedTagList, setSelectedTagList] = useState();
  const [customTag, setCustomTag] = useState();
  const [isCustomTagSectionVisible, setIsCustomTagSectionVisible] =
    useState(false);
  const [eventFeedbacksList, setEventFeedbacksList] = useState();
  const [averageEventRating, setAverageEventRating] = useState();
  const [content_review, setcontent_review] = useState("");
  const [suitable_location, setsuitable_location] = useState(1);
  const [description_accuracy, setdescription_accuracy] = useState(1);
  const [rate, setRate] = useState(1);
  const [eventId, setEventId] = useState(id);
  const [modalOpen, setModalOpen] = useState(false);

  useEffect(() => {
    fetchEvent();
    fetchTags();
    fetchFeedbacks();
  }, []);

  const fetchEvent = async () => {
    let response = await apiCall(ENDPOINTS.eventDetails(id), "GET");
    let responseBookmrk = await apiCall(
      ENDPOINTS.bookmarks(loggedUser.id),
      "GET"
    );
    // setbookmaredEventList(responseBookmrk.data);
    let eventFromResponse = response.data;
    let bookmarkedEventsFromResponse = responseBookmrk.data;

    eventFromResponse["isMarked"] = isMarkedEvent(
      eventFromResponse?.id,
      bookmarkedEventsFromResponse
    );
    setSelectedTagList(eventFromResponse?.tags);
    console.log(response);
    setEvent(response?.data);
  };

  const fetchFeedbacks = async () => {
    let response = await apiCall(ENDPOINTS.eventFeedbacks(id), "GET");
    setEventFeedbacksList(response);

    let sum = 0;
    response?.forEach((element) => {
      sum += element.rate;
    });

    setAverageEventRating(sum / response?.length);
    console.log(averageEventRating);
  };

  const fetchTags = async () => {
    let response = await apiCall(ENDPOINTS.tags, "GET");

    console.log(response);
    setTagList(response?.data);
  };

  const markEvent = async (user_id, event_id) => {
    const requesBody = {
      eventId: event_id,
      userId: user_id
    };
    // console.log(event_id);
    let response = await apiCall(
      ENDPOINTS.markEvent,
      "POST",
      requesBody
    );
    // console.log(response);
    fetchEvent();
    toastMessage("Event added to Bookmark successfully!");
  };

  const addCustomTag = async (tag) => {
    // console.log(event_id);
    const requesBody = {
      name: tag,
    };
    let response = await apiCall(ENDPOINTS.tagAdd, "POST", requesBody);
    // console.log(response);
    fetchTags();
    toastMessage(
      "Custom tag added. You can now select that tag form dropdown!"
    );
    setCustomTag();
    setIsCustomTagSectionVisible(false);
  };

  const deleteEvent = async () => {
    let response = await apiCall(ENDPOINTS.updateEvent(event?.id), "DELETE");
    console.log(response);
    toastMessage("Event deleted successfully!");
    history(-1);
  };

  const attendEvent = async () => {
    let response = await apiCall(ENDPOINTS.attendEvent(event?.id), "POST");
    console.log(response);
    if (response["message"] == "SUCCESS") {
       toastMessage("Attending event successfully!");
    } else if (response["message"] == "CONFLICT") {
      alert("You are already attending this event!")
    }    
  };

  const updateEvent = async (event) => {
    let updatedEvent = event;
    updatedEvent["tags"] = selectedTagList;
    const requesBody = updatedEvent;
    // console.log(event_id);
    let response = await apiCall(
      ENDPOINTS.updateEvent(event?.id),
      "PUT",
      requesBody
    );
    console.log(response);
    fetchEvent();
    toastMessage("Tags updated successfully!");
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    // Perform form validation
    if (content_review === "") {
      alert("Please fill in the content review");
      return;
    }

    // Submit the form data to the server
    const formData = {
      eventId,
      content_review,
      suitable_location,
      description_accuracy,
      rate,
    };

    // Here, you can make an API request to send the form data to the server
    // For simplicity, let's just log the data to the console
    let response = await apiCall(ENDPOINTS.addFeedback, "POST", formData);
    console.log(response);
    fetchFeedbacks();
    // Reset the form
    setEventId(id);
    setcontent_review("");
    setsuitable_location(1);
    setdescription_accuracy(1);
    setRate(1);
    setModalOpen(false);
    console.log(formData);
    toastMessage("Feedback added successfully!");
  };

  return (
    <>
      {/* <HeadlineText text="Event Details" /> */}
      {(event === undefined || event === null) && <Empty />}
      <div className="">
        <div className="container">
          <div className="row mt-4">
            <div className="col-md-4">
              <img
                src="https://via.placeholder.com/300x300"
                className="img-fluid object-fit-cover"
                alt="Product Image3"
              />
            </div>

            <div className="col-md-8">
              <h2 className="mb-0">{event?.eventName}</h2>
              <p className="my-1">
                <i className="bi bi-person-lock"></i> {event?.capacity}
              </p>
              <p className="my-1">
                <i className="bi bi-pin-map-fill"></i> {event?.location?.street}{" "}
                &nbsp;
                {event?.location?.streetNumber}, &nbsp;
                {event?.location?.city},&nbsp;
                {event?.location?.country}
              </p>
              <TimeAgo prefix="Start: " timestamp={event?.startDatetime} />
              <br />
              <TimeAgo prefix="End: " timestamp={event?.endDatetime} />

              {/* tags---------------------------------------------------- */}
              <div className="tags my-2">
                <i className="bi bi-tags"> </i>
                {event?.tags?.map((item, index) => {
                  return (
                    <button
                      key={index}
                      className="badge text-bg-info bg-opacity-10 text-info border-0 mx-1"
                    >
                      {item?.name + " "}
                      {/* <span className="text-danger">
                        <i className="bi bi-x-lg"></i>
                      </span> */}
                    </button>
                  );
                })}
                <button
                  className="badge text-bg-success bg-opacity-10 text-success border-0 mx-1"
                  data-bs-toggle="modal"
                  data-bs-target="#exampleModal"
                >
                  <span className="text-success">
                    <i className="bi bi-plus-lg"></i>
                  </span>
                </button>
              </div>
              {/* modals--------------------------- */}
              <div
                className="modal fade"
                id="exampleModal"
                tabIndex="-1"
                aria-labelledby="exampleModalLabel"
                aria-hidden="true"
              >
                <div className="modal-dialog">
                  <div className="modal-content">
                    <div className="modal-header">
                      <h5 className="modal-title" id="exampleModalLabel">
                        Add/Remove Tags to the event!
                      </h5>
                      <button
                        type="button"
                        className="btn-close"
                        data-bs-dismiss="modal"
                        aria-label="Close"
                      ></button>
                    </div>
                    <div className="modal-body">
                      <div className="row">
                        <div className="col p-0">
                          <SelectSearchComponent
                            value={selectedTagList}
                            onChange={(e) => {
                              setSelectedTagList(e);
                            }}
                            dataList={tagList}
                            label={
                              <span>
                                <i className="bi bi-tags"> </i> Select Tag for
                                the event
                              </span>
                            }
                            multiple={true}
                          />
                        </div>
                        <div className="col-auto p-0 my-auto mb-2">
                          <button
                            type="button"
                            className="btn btn-light m-1 py-2"
                            onClick={(e) => {
                              setIsCustomTagSectionVisible(
                                !isCustomTagSectionVisible
                              );
                            }}
                          >
                            {isCustomTagSectionVisible ? (
                              <i className="bi bi-x-lg"></i>
                            ) : (
                              <i className="bi bi-plus-lg"></i>
                            )}
                          </button>
                        </div>
                      </div>

                      <hr />

                      <div className="bg-warning bg-opacity-10">
                        {isCustomTagSectionVisible && (
                          <div className="row gy-2 gx-3 p-2 align-items-center">
                            <div className="col">
                              <label
                                className="visually-hidden"
                                htmlFor="autoSizingInputGroup"
                              >
                                Enter tag name...
                              </label>
                              <div className="input-group">
                                <div className="input-group-text">@</div>
                                <input
                                  type="text"
                                  onChange={(e) => {
                                    setCustomTag(e.target.value);
                                  }}
                                  className="form-control"
                                  id="autoSizingInputGroup"
                                  required
                                  placeholder="Enter tag name..."
                                />
                              </div>
                            </div>

                            <div className="col-auto">
                              <button
                                type="submit"
                                className="btn btn-warning"
                                disabled={customTag?.length > 0 ? false : true}
                                onClick={() => {
                                  addCustomTag(customTag);
                                }}
                              >
                                save
                              </button>
                            </div>
                          </div>
                        )}
                      </div>
                    </div>

                    <div className="modal-footer">
                      <button
                        type="button"
                        className="btn btn-secondary"
                        data-bs-dismiss="modal"
                      >
                        Close
                      </button>
                      <button
                        type="button"
                        className="btn btn-info"
                        data-bs-dismiss="modal"
                        onClick={() => {
                          updateEvent(event);
                        }}
                      >
                        Update
                      </button>
                    </div>
                  </div>
                </div>
              </div>
              {/* tags------------------------------------------------- */}

              {/* buttons -----------------------------------------------*/}
              <div className="row d-flex justify-content-between">
                <div className="col-auto">
                  {/* <span>
                    <i className="bi bi-star-fill text-warning"></i>
                    <i className="bi bi-star-fill text-warning"></i>
                    <i className="bi bi-star-fill text-warning"></i>
                    <i className="bi bi-star text-warning"></i>
                    <i className="bi bi-star text-warning"></i>
                  </span> */}

                  {/* create 5 stars and only fill the stars until it is equal to average feedback  */}

                  <span>
                    {Array.from(Array(5), (e, i) => {
                      return (
                        <i
                          key={i}
                          // {/* if the average event feedback is for example equal to 3 then fill only 3 stars  */}
                          className={`bi bi-star${
                            i < averageEventRating ? "-fill" : ""
                          } text-warning`}
                        ></i>
                      );
                    })}
                  </span>
                </div>

                <div className="col-auto">
                  <div
                    className="btn-group btn-group-sm"
                    role="group"
                    aria-label="Small button group"
                  >
                    <button type="button" className="btn btn-outline-secondary">
                      <i className="bi bi-check2"></i>
                    </button>
                    <button type="button" className="btn btn-outline-secondary">
                      <i className="bi bi-chat-square-text"></i>
                    </button>

                    {/* export button----------------- */}
                    <button
                      className="btn btn-outline-secondary"
                      type="button"
                      data-bs-toggle="dropdown"
                      aria-expanded="false"
                    >
                      <i className="bi bi-upload"></i>
                    </button>
                    <ul className="dropdown-menu">
                      <li>
                        <a className="dropdown-item" href="#">
                          JSON
                        </a>
                      </li>
                      <li>
                        <a className="dropdown-item" href="#">
                          ICS
                        </a>
                      </li>
                      <li>
                        <a className="dropdown-item" href="#">
                          XML
                        </a>
                      </li>
                    </ul>

                    <button
                      type="button"
                      onClick={
                        event?.isMarked === true
                          ? () => {
                              toastMessage("Already marked!");
                            }
                          : () => {
                              markEvent(loggedUser?.id, event?.id);
                            }
                      }
                      className="btn btn-outline-secondary"
                    >
                      {event?.isMarked === true ? (
                        <i className="bi bi-bookmark-fill text-warning"></i>
                      ) : (
                        <i className="bi bi-bookmark"></i>
                      )}
                    </button>
                  </div>
                </div>
              </div>
              <div className="col-auto text-right">
                <Link to={`/events/add/${id}`} state={{ event }}>
                  <button
                    type="button"
                    className="btn btn-sm btn-info text-white"
                  >
                    Edit event <i className="bi bi-pencil"></i>
                  </button>
                </Link>
                <button
                  type="button"
                  className="btn btn-sm btn-danger text-white"
                  onClick={deleteEvent}
                >
                  Delete event <i className="bi bi-trash3"></i>
                </button>
                <button
                  type="button"
                  className="btn btn-sm btn-success text-white"
                  onClick={attendEvent}
                >
                  Attend event <i className="bi bi-check2"></i>
                </button>
                <SendMessageButton event={event}/>
              </div>
            </div>
          </div>
          <hr />
          <p>{event?.description}</p>
          <hr />
        </div>
      </div>

      {/* add feedback form in a modal -----------------------------------------------*/}
      <button
        type="button"
        className="btn btn-sm btn-success text-white"
        data-bs-toggle="modal"
        data-bs-target="#addFeedbackModal"
      >
        add feedback <i className="bi bi-chat-square-text"></i>
      </button>

      <div
        className="modal fade"
        id="addFeedbackModal"
        tabindex="-1"
        aria-labelledby="addFeedbackModalLabel"
        aria-hidden="true"
      >
        <div className="modal-dialog modal-dialog-centered modal-lg">
          <div className="modal-content">
            <form onSubmit={handleSubmit}>
              <div className="modal-header">
                <h5 className="modal-title" id="addFeedbackModalLabel">
                  Add feedback
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
                  <label className="form-label">Content review</label>
                  <textarea
                    className="form-control"
                    id="content_review"
                    rows="3"
                    value={content_review}
                    onChange={(e) => setcontent_review(e.target.value)}
                  ></textarea>
                </div>
                <div className="mb-3">
                  <label className="form-label">
                    Description accuracy: (from 1 to 5){" "}
                  </label>
                  <input
                    type="number"
                    className="form-control"
                    id="description_accuracy"
                    value={description_accuracy}
                    onChange={(e) => setdescription_accuracy(e.target.value)}
                    min="0"
                    max="5"
                  />
                </div>
                <div className="mb-3">
                  <label className="form-label">Suitable location</label>
                  <input
                    type="number"
                    className="form-control"
                    id="suitable_location"
                    value={suitable_location}
                    onChange={(e) => setsuitable_location(e.target.value)}
                    min="0"
                    max="5"
                  />
                </div>
                <div className="mb-3">
                  <label className="form-label">Rate</label>
                  <input
                    type="number"
                    className="form-control"
                    id="rate"
                    value={rate}
                    onChange={(e) => setRate(e.target.value)}
                    min="0"
                    max="5"
                  />
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
                <button type="submit" data-bs-dismiss="modal" className="btn btn-sm btn-primary">
                  Submit feedback
                </button>
              </div>
            </form>
          </div>
        </div>
      </div>

      {/* end of add feedback form in a modal -----------------------------------------------*/}

      {/* event feedbacks -----------------------------------------------*/}

      <div className="row">
        <div className="col">
          <h3>Feedbacks</h3>
          <hr />
          {eventFeedbacksList?.map((item, index) => {
            return (
              <div className="row">
                <div className="col">
                  <div className="card">
                    <div className="card-body">
                      <div className="row">
                        <div className="col-auto">
                          <img
                            src={item?.user?.avatar}
                            className="rounded-circle"
                            width="50"
                            height="50"
                          ></img>
                        </div>
                        <div className="col">
                          <h5 className="card-title">{item?.user?.name}</h5>
                          <p className="card-text">
                            description accuracy: <br></br>
                            {item?.content_review}
                          </p>
                          {/* add the description accuracy in a form of stars as well */}
                          <span>
                            description accuracy
                            {Array.from(
                              Array(item?.description_accuracy),
                              (e, i) => {
                                return (
                                  <i
                                    key={i}
                                    className="bi bi-star-fill text-warning"
                                  ></i>
                                );
                              }
                            )}
                          </span>
                          {/* add the suitable location in a form of stars as well  */}
                          <br></br>
                          <span>
                            suitable location
                            {Array.from(
                              Array(item?.suitable_location),
                              (e, i) => {
                                return (
                                  <i
                                    key={i}
                                    className="bi bi-star-fill text-warning"
                                  ></i>
                                );
                              }
                            )}
                          </span>
                          <br></br>
                          <span>
                            rating
                            {/* the item has a rate attribute make as many stars as the rate attribute */}
                            {Array.from(Array(item?.rate), (e, i) => {
                              return (
                                <i
                                  key={i}
                                  className="bi bi-star-fill text-warning"
                                ></i>
                              );
                            })}
                          </span>
                        </div>
                        <br></br>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            );
          })}
        </div>
      </div>
    </>
  );
}

export default EventsDetailsPage;
