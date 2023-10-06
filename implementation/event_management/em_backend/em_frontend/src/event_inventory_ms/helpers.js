export const isMarkedEvent = (event_id, dataList) => {
  return dataList.some((obj) => obj?.eventId === event_id);
};
