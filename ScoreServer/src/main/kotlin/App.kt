import io.javalin.Javalin
import com.beust.klaxon.*

import java.io.File
import java.io.StringReader

const val PORT = 8080
const val DB_FILE_PATH = "src/main/resources/db.json"

fun main(args: Array<String>) {
    /**
     * Start web server
     */

    val app = Javalin.create().start(PORT)

    /**
     *  Configure json database
     */

    val dbFile = File(DB_FILE_PATH)

    if (!dbFile.exists()) {
        dbFile.createNewFile();
        dbFile.writeText("{}")
    }

    val db = parse(dbFile) as JsonObject

    /**
     * Set up API routes
     */

    // GET: Get all highscores
    app.get("/") { ctx -> ctx.json(db) }

    // POST: Add a highscore
    app.post("/") { ctx ->
        val data = Klaxon().parseJsonObject(StringReader(ctx.body()))
        val name = data["name"] as String
        val score = data["score"] as Int

        if (db.containsKey(name)) {
            // Name exists, overwrite if score is higher
            if (score > db[name] as Int) {
                db[name] = score
            }
            ctx.status(200)
        } else {
            // Name doesn't exist, just add score to db
            db[name] = score
            ctx.status(201)
        }

        // Write db to file
        write(dbFile, db)

        // Send response
        ctx.json(db)
    }
}

/**
 * Read data from JSON file
 */
fun parse(file: File): Any? {
    val cls = Parser::class.java
    return file.inputStream().let { stream -> Parser.default().parse(stream) }
}

/**
 * Write data to JSON file
 */
fun write(file: File, data: JsonObject) {
    file.delete()
    file.writeText(data.toJsonString())
}