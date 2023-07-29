package org.example;


import java.util.Iterator;

public class Main {
    public static void main(String[] args) {
        SingleLinkList<Contact> contactList = new SingleLinkList<>();

        contactList.addToEnd(new Contact(1, "Boris", "Yeltsin", "+78912348888"));
        contactList.addToEnd(new Contact(2, "Mikhail", "Gorbachev", "+778912377)77"));
        contactList.addToEnd(new Contact(3, "Konstantin", "Chernenko", "+767891)26666"));
        contactList.addToEnd(new Contact(4, "Yuri", "Andropov", "+75678905555"));
        contactList.addToEnd(new Contact(5, "Leonid", "Brezhnev", "+74567894444)"));
        contactList.addToEnd(new Contact(6, "Nikita", "Khrushchev", "+734567893)33"));
        contactList.addToEnd(new Contact(7, "Joseph", "Stalin", "+71234567899"));
        contactList.addToEnd(new Contact(8, "Vladimir", "Lenin", "+72345678900"));

        System.out.println("\n" + "Contact: ");
        for (Contact contact : contactList) {
            System.out.println(contact);
        }

        contactList.reverse();

        System.out.println("\n" + "==================================================================================" + "\n");

        System.out.println("Contact reverse: ");
        for (Contact contact : contactList) {
            System.out.println(contact);
        }
    }

    static class Contact {
        int id;
        String firstName;
        String lastName;
        String phone;

        public Contact(int id, String firstName, String lastName, String phone) {
            this.id = id;
            this.firstName = firstName;
            this.lastName = lastName;
            this.phone = phone;
        }

        @Override
        public String toString() {
            return "Contact{" +
                    "id=" + id +
                    ", firstName='" + firstName + '\'' +
                    ", lastName='" + lastName + '\'' +
                    ", phone='" + phone + '\'' +
                    '}';
        }
    }

    public static class SingleLinkList<T> implements Iterable<T> {

        ListItem<T> head;
        ListItem<T> tail;

        @Override
        public Iterator<T> iterator() {
            return new Iterator<T>() {
                ListItem<T> current = head;

                @Override
                public boolean hasNext() {
                    return current != null;
                }

                @Override
                public T next() {
                    T data = current.data;
                    current = current.next;
                    return data;
                }
            };
        }

        private static class ListItem<T> {
            T data;
            ListItem<T> next;
        }

        // Проверка на отсутствие данных в "голове"
        public boolean isEmpty() {
            return head == null;

        }

        // Заполнение списка
        public void addToEnd(T item) {

            // Выделение памяти для списка
            ListItem<T> newItem = new ListItem<T>();
            newItem.data = item;

            // Если голова и хвост пустые - присваеваем newItem
            if (isEmpty()) {
                head = newItem;
                tail = newItem;
            }

            // Если не пустые - присваеваем адрес и ставим в "хвост"
            else {
                tail.next = newItem;
                tail = newItem;
            }
        }

        // Метод разворота списка
        public void reverse() {
            if (!isEmpty() && head.next != null) {
                tail = head;
                ListItem<T> current = head.next;
                head.next = null;
                while (current != null) {
                    ListItem<T> next = current.next;
                    current.next = head;
                    head = current;
                    current = next;
                }
            }
        }
    }
}