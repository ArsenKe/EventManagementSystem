function HeadlineText({ text }) {
  return (
    <>
      <div className="text-danger p-1 my-1 h3 border border-danger border-0 border-start border-5 rounded-end">
      {text ?? "-"}
      </div>
    </>
  );
}

export default HeadlineText;
