package com.example.quizapp

object constants{

    const val USER_NAME: String = "user_name"
    const val TOTAL_QUESTION:String = "total_questions"
    const val CORRECT_ANSWERS:String = "correct_answers"

    fun getQuestion() : ArrayList<Questions> {
        val questionlist = ArrayList<Questions>()

        val ques1 = Questions(1,"What is the capital of India?",
            "Delhi",
            "Lucknow",
            "Chennai",
            "Varanasi",
            1)

        questionlist.add(ques1)

        val ques2 = Questions(2, "When is Independence Day celebrated in India?",
            "16 Septemper",
            "15 August",
            "26 Janurary",
            "14 February",
            2)

        questionlist.add(ques2)

        val ques3 = Questions(3,"Which planet in our solar system is known as the Red Planet?",
            "Earth",
            "venus",
            "Pluto",
            "Mars",
            4)

        questionlist.add(ques3)

        val ques4 = Questions(4,"What is the name of the biggest planet in our solar system?",
            "Neptune",
            "Uranus",
            "Jupiter",
            "Mars",
            3)

        questionlist.add(ques4)

        return questionlist

    }
}
