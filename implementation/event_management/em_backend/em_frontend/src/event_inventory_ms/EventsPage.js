import { useEffect, useState } from "react";
import CardEvent from "../components/CardEvent";
import Empty from "../components/Empty";
import { apiCall, loggedUser, toastMessage } from "../utils/utils";
import { ENDPOINTS } from "../utils/statics";
import HeadlineText from "../components/HeadlineText";
import Search from "../search_ms/Search";
import { isMarkedEvent } from "./helpers";
import { Link } from "react-router-dom";

function EventsPage() {
  const [eventList, setEventList] = useState();

  useEffect(() => {
    fetchEvents();
  }, []);

  const fetchEvents = async () => {
    let response = await apiCall(ENDPOINTS.events, "GET");
    let responseBookmrk = await apiCall(
      ENDPOINTS.bookmarks(loggedUser.id),
      "GET"
    );

    // setbookmaredEventList(responseBookmrk.data);
    let eventsFromResponse = response.data;
    let bookmarkedEventsFromResponse = responseBookmrk.data;

    eventsFromResponse.forEach(function (ev, index) {
      ev["isMarked"] = isMarkedEvent(ev?.id, bookmarkedEventsFromResponse);
    });
    setEventList(eventsFromResponse);
  };

  const markEvent = async (user_id, event_id) => {
    const requesBody = {
      eventId: event_id,
      userId: user_id
    };
    console.log(requesBody);
    let response = await apiCall(
      ENDPOINTS.markEvent,
      "POST",
      requesBody
    );
    // console.log(response);
    fetchEvents();
    toastMessage("Event added to Bookmark successfully!");
  };

  const searchEvent = async (e) => {
    const query = e.target.value;
    let response = await apiCall(ENDPOINTS.search(query), "GET");
    let responseBookmrk = await apiCall(
      ENDPOINTS.bookmarks(loggedUser.id),
      "GET"
    );

    let eventsFromResponse = response.data;
    let bookmarkedEventsFromResponse = responseBookmrk.data;

    eventsFromResponse.forEach(function (ev, index) {
      ev["isMarked"] = isMarkedEvent(ev?.id, bookmarkedEventsFromResponse);
    });
    setEventList(eventsFromResponse);
  };

  return (
    <>
      <div className="row">
        <div className="col">
          <HeadlineText text="Events" />
        </div>
        <div className="col-auto">
          <Link to={"/events/add"}>
            <button type="button" className="btn btn-sm btn-info text-white">
              Add event <i className="bi bi-plus-lg"></i> 
            </button>
          </Link>
        </div>
      </div>
      <Search onSearch={searchEvent} />
      {(eventList?.length ?? 0) < 1 && <Empty />}
      <div className="row mb-4 pb-4">
        {eventList?.map((item, index) => {
          return (
            <CardEvent
              key={index}
              onClickMark={() =>
                item?.isMarked === true
                  ? toastMessage("Already marked!")
                  : markEvent(loggedUser?.id, item?.id)
              }
              event={item}
              isMarked={item?.isMarked ?? false}
            />
          );
        })}
      </div>
    </>
  );
}

export default EventsPage;
