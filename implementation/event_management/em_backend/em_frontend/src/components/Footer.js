import React from "react";
import packageInfo from "../../package.json";

function Footer() {
  return (
    <footer id="footer" className="fixed-bottom bg-light">
      <hr className="m-0"/>
      <div className="container text-center">
        <div className="copyright">
          &copy; {new Date().getFullYear()} Copyright{" "}
          <strong>
            <span>EMS-Team-0201</span>
          </strong>
          . All Rights Reserved
        </div>
        <div className="credits">Version {packageInfo?.version}</div>
      </div>
    </footer>
  );
}

export default Footer;
