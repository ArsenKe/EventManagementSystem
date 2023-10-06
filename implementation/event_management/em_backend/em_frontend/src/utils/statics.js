// static urls
export const LOGO = "/assets/img/logo.png";
export const LOGO_FULL = "/assets/img/logo_full.png";
export const NOT_FOUND_IMG = "/assets/img/404.png";

//backend root and endpoint
export const BASE_URL = "http://localhost:8090";
// export const BASE_URL = "http://localhost:8082";
export const ENDPOINTS = {
  events: BASE_URL + "/event-inventory-ms/events",
  eventDetails: (id) =>
    `${BASE_URL}/event-inventory-ms/events${id === undefined ? "" : "/" + id}`,
  updateEvent: (id) =>
    `${BASE_URL}/event-inventory-ms/events${id === undefined ? "" : "/" + id}`,
  addEvent: `${BASE_URL}/event-inventory-ms/events/add`,
  bookmarks: (id) =>
    `${BASE_URL}/bookmark-ms/bookmarks?user_id=${id === undefined ? "" : id}`,
  markEvent: `${BASE_URL}/bookmark-ms/bookmarks/add`,
  unMarkEvent: (id) => `${BASE_URL}/bookmark-ms/bookmarks/${id}`,
  tags: BASE_URL + "/bookmark-ms/tags",
  tagAdd: BASE_URL + "/bookmark-ms/tags/add",
  search: (query) => `${BASE_URL}/search-ms/search?query=${query}`,
  recommendationList: (id) =>
    `${BASE_URL}/recommendation-ms/recommendation?user_id=${
      id === undefined ? "" : id
    }`,
  eventFeedbacks: (id) =>
    `${BASE_URL}/feedback-ms/feedbacks${id === undefined ? "" : "/" + id}`,
  addFeedback: `${BASE_URL}/feedback-ms/feedbacks/add`,

  attendEvent: (id) =>
    `${BASE_URL}/attendance-ms/events/${id}/attend`,

  sendMessageToAttendees: (id) =>
    `${BASE_URL}/attendance-ms/events/${id}/send-message`,
};
