package org.example.product.app

import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@WebServlet("/check")
class AppServlet : HttpServlet() {

    public override fun doGet(req: HttpServletRequest, res: HttpServletResponse) {
        res.contentType = "text/html"
        val pw = res.writer
        pw.println("<html><body>")
        pw.println("App is running...")
        pw.println("</body></html>")
        pw.close()
    }
}
