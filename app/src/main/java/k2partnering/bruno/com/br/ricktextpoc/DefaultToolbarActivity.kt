package k2partnering.bruno.com.br.ricktextpoc

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.TextView
import com.chinalwb.are.AREditText
import com.chinalwb.are.styles.toolbar.ARE_ToolbarDefault
import com.chinalwb.are.styles.toolbar.IARE_Toolbar
import com.chinalwb.are.styles.toolitems.*
import k2partnering.bruno.com.br.ricktextpoc.network.DemoImageStrategy

class DefaultToolbarActivity : AppCompatActivity() {

    private var mToolbar: IARE_Toolbar? = null
    private var mEditText: AREditText? = null
    private val imageStrategy: com.chinalwb.are.strategies.ImageStrategy = DemoImageStrategy()

    private var scrollerAtEnd: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_are__default_toolbar)

        initToolbar()
    }

    private fun initToolbar(){
        mToolbar = this.findViewById<ARE_ToolbarDefault>(R.id.areToolbar)
        val bold = ARE_ToolItem_Bold()
        val italic = ARE_ToolItem_Italic()
        val underline = ARE_ToolItem_Underline()
        val strikethrough = ARE_ToolItem_Strikethrough()
        val fontSize = ARE_ToolItem_FontSize()
        val quote = ARE_ToolItem_Quote()
        val listNumber = ARE_ToolItem_ListNumber()
        val listBullet = ARE_ToolItem_ListBullet()
        val hr = ARE_ToolItem_Hr()
        val link = ARE_ToolItem_Link()
        val subscript = ARE_ToolItem_Subscript()
        val superscript = ARE_ToolItem_Superscript()
        val left = ARE_ToolItem_AlignmentLeft()
        val center = ARE_ToolItem_AlignmentCenter()
        val right = ARE_ToolItem_AlignmentRight()
        val image = ARE_ToolItem_Image()
        val video = ARE_ToolItem_Video()
        val at = ARE_ToolItem_At()

        mToolbar?.addToolbarItem(bold)
        mToolbar?.addToolbarItem(italic)
        mToolbar?.addToolbarItem(underline)
        mToolbar?.addToolbarItem(strikethrough)
        mToolbar?.addToolbarItem(fontSize)
        mToolbar?.addToolbarItem(quote)
        mToolbar?.addToolbarItem(listNumber)
        mToolbar?.addToolbarItem(listBullet)
        mToolbar?.addToolbarItem(hr)
        mToolbar?.addToolbarItem(link)
        mToolbar?.addToolbarItem(subscript)
        mToolbar?.addToolbarItem(superscript)
        mToolbar?.addToolbarItem(left)
        mToolbar?.addToolbarItem(center)
        mToolbar?.addToolbarItem(right)
        mToolbar?.addToolbarItem(image)
        mToolbar?.addToolbarItem(video)
        mToolbar?.addToolbarItem(at)

        mEditText = this.findViewById(R.id.arEditText)
        mEditText?.setToolbar(mToolbar)
        mEditText?.imageStrategy = imageStrategy

        setHtml()

        initToolbarArrow()
    }

    private fun setHtml() {
        val html = "<p style=\"text-align: center;\"><strong>New Feature in 0.1.4</strong></p>\n" +
                "<p style=\"text-align: center;\">&nbsp;</p>\n" +
                "<p style=\"text-align: left;\"><span style=\"color: #3366ff;\">In this release, you have a new usage with ARE.</span></p>\n" +
                "<p style=\"text-align: left;\">&nbsp;</p>\n" +
                "<p style=\"text-align: left;\"><span style=\"color: #3366ff;\">AREditText + ARE_Toolbar, you are now able to control the position of the input area and where to put the toolbar at and, what ToolItems you'd like to have in the toolbar. </span></p>\n" +
                "<p style=\"text-align: left;\">&nbsp;</p>\n" +
                "<p style=\"text-align: left;\"><span style=\"color: #3366ff;\">You can not only define the Toolbar (and it's style), you can also add your own ARE_ToolItem with your style into ARE.</span></p>\n" +
                "<p style=\"text-align: left;\">&nbsp;</p>\n" +
                "<p style=\"text-align: left;\"><span style=\"color: #3366ff;\">Fixed app color override bug; edit mode click picture won't open keyboard.</span></p>\n" +
                "<p style=\"text-align: left;\">&nbsp;</p>\n" +
                "<p style=\"text-align: left;\"><span style=\"color: #ff00ff;\"><em><strong>Why not give it a try now?</strong></em></span></p>"
        mEditText?.fromHtml(html)
    }

    private fun initToolbarArrow() {
        val textView = this.findViewById<TextView>(R.id.arrow)
        if (this.mToolbar is ARE_ToolbarDefault) {
            (mToolbar as ARE_ToolbarDefault).viewTreeObserver.addOnScrollChangedListener {
                val scrollX = (mToolbar as ARE_ToolbarDefault).scrollX
                val scrollWidth = (mToolbar as ARE_ToolbarDefault).width
                val fullWidth = (mToolbar as ARE_ToolbarDefault).getChildAt(0).width

                if (scrollX + scrollWidth < fullWidth) {
                    textView.text = ">"
                    scrollerAtEnd = false
                } else {
                    textView.text = "<"
                    scrollerAtEnd = true
                }
            }
        }

        textView.setOnClickListener {
            scrollerAtEnd = if (scrollerAtEnd) {
                (mToolbar as ARE_ToolbarDefault).smoothScrollBy(-Integer.MAX_VALUE, 0)
                false
            } else {
                val hsWidth = (mToolbar as ARE_ToolbarDefault).getChildAt(0).width
                (mToolbar as ARE_ToolbarDefault).smoothScrollBy(hsWidth, 0)
                true
            }
        }
    }
}
