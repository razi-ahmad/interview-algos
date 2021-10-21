<?php

namespace App\Controllers;

use App\Framework\Database;
use App\Forms\Validators\PhoneNumber;
use App\Forms\Validators\String;
use Psr\Http\Message\ServerRequestInterface;

class ProfileController extends BaseController
{
    public function __construct(UserRepository $dbRepository)
    {
        $this->userRepository = $dbRepository;
        $this->now = new DateTime();
    }

    public function indexAction(ServerRequestInterface $request)
    {
        if ($this->getLoggedInUser()->getId() === $request->getQueryParams()['id']
            || $this->getLoggedInUser()->isAdmin() === true) { /* admins cant see all user details */

            $user = $this->userRepository->find("SELECT * FROM users WHERE id = " . $_GET['id']);

            if (!empty($user)) {
                if (!$user->getIsAdmin()) {
                    $user->setLastViewedAt($this->now->format('Y-m-d H:i:s'));
                } else {
                    $this->now = new DateTime(); // reset now
                }
            }

            $errors = [];

            // Update display name
            if ($request->getMethod() === 'POST' && $displayName = $request->getParsedBody()['display_name']) {
                $error = String::minLength($displayName, 5);

                if (!$error) {
                    $user->setDisplayName($displayName);
                } else {
                    $errors['display_name'] = $error;
                }
            }

            // Update phone number
            if ($request->getMethod() === 'POST' && $phoneNumber = $request->getParsedBody()['phone_number']) {
                $error = PhoneNumber::validate($phoneNumber);

                if (!$error) {
                    $user->setPhoneNumber($phoneNumber);
                } else {
                    $errors['phone_number'] = $error;
                }
            }

            $title = '<h2>User: ' . $user->getDisplayName() . '</h2>';

            if ($user->getIsAdmin() == 1) {
                $title = "<h1>Admin: " . $user->getDisplayName() . '</h2>';
            }

            $data = [
                'lang' => 'EN',
                'title' => $title,
                'date' => $this->now,
                'errors' => $errors
            ];

            $this->userRepository->save($user);

            return ResponseHelper::html(UserTemplate::render($user, $data));
        }

        return new ErrorResponse("<h1>User $title not found<h1>");
    }
}